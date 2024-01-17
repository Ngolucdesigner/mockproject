package com.anks.tech.ecommerce.Services.Account;

import com.anks.tech.ecommerce.Entity.Account;

import com.anks.tech.ecommerce.Entity.FileProduct;
import com.anks.tech.ecommerce.Form.AccountForm;
import com.anks.tech.ecommerce.Respository.IAccountRepository;
import com.anks.tech.ecommerce.Respository.IFileProductRespository;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class AccountServices implements IAccountServices{

    @Autowired
    private IAccountRepository accountRepository;
    @Autowired
    private IFileProductRespository fileProductRespository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public Page<Account> getAllAccounts(Pageable pageable) {
        return accountRepository.findAll(pageable);
    }

    @Override
    public void createNewAccount(AccountForm form) {
        TypeMap typeMap = modelMapper.getTypeMap(AccountForm.class, Account.class);
        if(typeMap==null){
            modelMapper.addMappings(new PropertyMap<AccountForm, Account>() {
                @Override
                protected void configure() {
                    skip(destination.getId() );
                }
            });
        }

        Account account = modelMapper.map(form,Account.class);
        accountRepository.save(account);

        FileProduct fileAvatar = account.getFileProduct();
        fileProductRespository.save(fileAvatar);
    }

}
