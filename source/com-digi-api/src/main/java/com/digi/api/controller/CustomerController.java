package com.digi.api.controller;

import com.digi.api.constant.DigiConstant;
import com.digi.api.dto.ApiMessageDto;
import com.digi.api.dto.ResponseListObj;
import com.digi.api.dto.account.VerifyDto;
import com.digi.api.dto.customer.CustomerAdminDto;
import com.digi.api.dto.customer.CustomerAutoCompleteDto;
import com.digi.api.dto.customer.CustomerClientDto;
import com.digi.api.exception.UnauthorizationException;
import com.digi.api.form.customer.CreateCustomerForm;
import com.digi.api.form.customer.UpdateByCustomerForm;
import com.digi.api.form.customer.UpdateCustomerForm;
import com.digi.api.mapper.CustomerMapper;
import com.digi.api.service.EmailService;
import com.digi.api.service.QRCodeApiService;
import com.digi.api.service.QRCodeOTPService;
import com.digi.api.storage.criteria.CustomerAutoCompleteCriteria;
import com.digi.api.storage.criteria.CustomerCriteria;
import com.digi.api.storage.model.Account;
import com.digi.api.storage.model.Customer;
import com.digi.api.storage.model.Group;
import com.digi.api.storage.repository.AccountRepository;
import com.digi.api.storage.repository.CustomerRepository;
import com.digi.api.storage.repository.GroupRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/customer")
@Slf4j
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class CustomerController extends ABasicController {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    GroupRepository groupRepository;
    @Autowired
    private QRCodeOTPService qrCodeOTPService;
    @Autowired
    private EmailService emailService;
    PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    @Autowired
    private QRCodeApiService qrCodeApiService;


    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<ResponseListObj<CustomerAdminDto>> list(CustomerCriteria customerCriteria, Pageable pageable) {
        if (!isAdmin()) {
            throw new UnauthorizationException("Not allowed to list");
        }
        ApiMessageDto<ResponseListObj<CustomerAdminDto>> apiMessageDto = new ApiMessageDto<>();

        Page<Customer> customerPage = customerRepository.findAll(customerCriteria.getSpecification(), pageable);
        ResponseListObj<CustomerAdminDto> responseListObj = new ResponseListObj<>();
        responseListObj.setData(customerMapper.fromAdminEntityListToDtoList(customerPage.getContent()));
        responseListObj.setPage(pageable.getPageNumber());
        responseListObj.setTotalPage(customerPage.getTotalPages());
        responseListObj.setTotalElements(customerPage.getTotalElements());

        apiMessageDto.setData(responseListObj);
        apiMessageDto.setMessage("List customer success");
        return apiMessageDto;
    }

    @GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<CustomerAdminDto> get(@PathVariable Long id) {
        if (!isAdmin()) {
            throw new UnauthorizationException("Not allowed to get");
        }

        ApiMessageDto<CustomerAdminDto> apiMessageDto = new ApiMessageDto<>();
        Customer customer = customerRepository.findById(id).orElse(null);
        if (customer == null) {
            apiMessageDto.setResult(false);
            apiMessageDto.setMessage("Can not get customer");
            return apiMessageDto;
        }

        CustomerAdminDto customerAdminDto = customerMapper.fromAdminEntityToDto(customer);
        apiMessageDto.setData(customerAdminDto);
        apiMessageDto.setMessage("Get customer success");
        return apiMessageDto;
    }

    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<String> update(@Valid @RequestBody UpdateCustomerForm updateCustomerForm,
            BindingResult bindingResult) {
        if (!isAdmin()) {
            throw new UnauthorizationException("Not allowed to update");
        }
        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();
        Customer customer = customerRepository.findById(updateCustomerForm.getId()).orElse(null);
        if (customer == null) {
            apiMessageDto.setResult(false);
            apiMessageDto.setMessage("customer's id doesn't exist");
        }
        customerMapper.fromUpdateFormToEntity(updateCustomerForm, customer);
        Account account = accountRepository.findById(updateCustomerForm.getId()).orElse(null);
        customerMapper.fromUpdateFormToAccount(updateCustomerForm, account);
        accountRepository.save(account);
        customer.setAccount(account);
        customerRepository.save(customer);
        apiMessageDto.setMessage("Update customer success");
        return apiMessageDto;
    }

    @Transactional
    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<String> delete(@PathVariable Long id) {
        if (!isAdmin()) {
            throw new UnauthorizationException("Not allowed to delete");
        }

        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();
        Customer customer = customerRepository.findById(id).orElse(null);
        if (customer == null) {
            apiMessageDto.setResult(false);
            apiMessageDto.setMessage("customer doesn't exist to delete");
            return apiMessageDto;
        }

        customerRepository.deleteById(id);
        accountRepository.deleteById(id);
        apiMessageDto.setMessage("Delete customer success");
        return apiMessageDto;
    }

    @GetMapping(value = "/get-profile", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<CustomerClientDto> getProfile() {
        ApiMessageDto<CustomerClientDto> apiMessageDto = new ApiMessageDto<>();
        Long id = getCurrentUserId();
        Customer customer = customerRepository.findById(id).orElse(null);
        CustomerClientDto customerClientDto = customerMapper.fromClientEntityToDto(customer);
        apiMessageDto.setData(customerClientDto);
        apiMessageDto.setMessage("Get customer success");
        return apiMessageDto;
    }

    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<VerifyDto> register(@Valid @RequestBody CreateCustomerForm createCustomerForm, BindingResult bindingResult) {
        ApiMessageDto<VerifyDto> apiMessageDto = new ApiMessageDto<>();

        Group group = groupRepository.findFirstByKind(DigiConstant.GROUP_KIND_CUSTOMER);
        if (group == null) {
           apiMessageDto.setMessage("Lỗi không xác định, vui lòng liên hệ quản trị viên!");
           return apiMessageDto;
        }

        Long accountCheck = accountRepository
                .countAccountByUsernameOrEmailOrPhone(
                    createCustomerForm.getUsername(),
                    createCustomerForm.getEmail(), 
                    createCustomerForm.getPhone()
                );
        if (accountCheck > 0) {
            apiMessageDto.setResult(false);
            apiMessageDto.setMessage("Email hoặc số điện thoại hoặc tài khoản này đã được đăng ký, vui lòng chọn tài khoản!");
            return apiMessageDto;
        }

        // common component of 2 case
        Customer customer = customerMapper.fromCreateFormToEntity(createCustomerForm);
        Account account = customerMapper.fromCreateFormToAccount(createCustomerForm);
        account.setGroup(group);
        account.setKind(DigiConstant.USER_KIND_CUSTOMER);
        account.setStatus(DigiConstant.STATUS_PENDING);
        account.setVerifyTime(0);
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        String otp = qrCodeOTPService.generate(6);
        account.setOtp(otp);

        customer.setStatus(DigiConstant.STATUS_PENDING);
        customer.setAccount(account);

        VerifyDto verifyDto = new VerifyDto();
        customer = customerRepository.save(customer);
        emailService.sendEmail(createCustomerForm.getEmail(), otp, "verify code", true);

        verifyDto.setId(customer.getId());
        verifyDto.setUsername(createCustomerForm.getUsername());
        verifyDto.setEmail(createCustomerForm.getEmail());
        apiMessageDto.setData(verifyDto);
        apiMessageDto.setMessage("Đăng ký tài khoản thành công");
        return apiMessageDto;
    }

    @PutMapping(value = "/update-profile", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<String> updateProfile(@Valid @RequestBody UpdateByCustomerForm updateCustomerForm,
            BindingResult bindingResult) {
        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();
        Customer customer = customerRepository.findById(updateCustomerForm.getId()).orElse(null);
        if (customer == null) {
            apiMessageDto.setResult(false);
            apiMessageDto.setMessage("Người dùng không tồn tại!");
            return apiMessageDto;
        }
        customerMapper.fromUpdateFormByCustomerToCustomer(updateCustomerForm, customer);
        Account account = accountRepository.findById(updateCustomerForm.getId()).orElse(null);
        customerMapper.fromUpdateFormByCustomerToAccount(updateCustomerForm, account);
        if (updateCustomerForm.getOldPassword() != null && updateCustomerForm.getNewPassword() != null) {
            if (!passwordEncoder.matches(updateCustomerForm.getOldPassword(), account.getPassword())) {
                apiMessageDto.setResult(false);
                apiMessageDto.setMessage("Mật khẩu cũ không đúng!");
                return apiMessageDto;
            } else {
                account.setPassword(passwordEncoder.encode(updateCustomerForm.getNewPassword()));
            }
        }
        accountRepository.save(account);
        customer.setAccount(account);
        customerRepository.save(customer);
        apiMessageDto.setMessage("Cập nhật hồ sơ thành công!");
        return apiMessageDto;
    }

    @GetMapping(value = "/list-auto-complete", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<ResponseListObj<CustomerAutoCompleteDto>> listAutoComplete(CustomerAutoCompleteCriteria customerCriteria, Pageable pageable) {
        if (!isAdmin()) {
            throw new UnauthorizationException("Not allowed to list");
        }
        ApiMessageDto<ResponseListObj<CustomerAutoCompleteDto>> apiMessageDto = new ApiMessageDto<>();

        Page<Customer> customerPage = customerRepository.findAll(customerCriteria.getSpecification(), pageable);
        ResponseListObj<CustomerAutoCompleteDto> responseListObj = new ResponseListObj<>();
        responseListObj.setData(customerMapper.fromAutoCompleteEntityListToDtoList(customerPage.getContent()));
        responseListObj.setPage(pageable.getPageNumber());
        responseListObj.setTotalPage(customerPage.getTotalPages());
        responseListObj.setTotalElements(customerPage.getTotalElements());

        apiMessageDto.setData(responseListObj);
        apiMessageDto.setMessage("List customer success");
        return apiMessageDto;
    }
}
