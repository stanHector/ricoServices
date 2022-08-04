package service.ricotunes.giftcards.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import service.ricotunes.giftcards.dto.WalletDto;
import service.ricotunes.giftcards.exception.ResourceNotFoundException;
import service.ricotunes.giftcards.model.User;
import service.ricotunes.giftcards.model.Wallet;
import service.ricotunes.giftcards.repository.WalletRepository;
import service.ricotunes.giftcards.service.WalletService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class WalletController {
    private WalletRepository walletRepository;

    @Autowired
    WalletService walletService;

    public WalletController(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }


    @PutMapping("wallet/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Wallet updateWallet(@PathVariable("id") Long id, @Valid @RequestBody WalletDto walletDto) throws ResourceNotFoundException {
        System.out.println("Update Wallet with ID = " + id + "...");
        Wallet wallet = walletRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Wallet not found for this id: " + id));

        wallet.setCurrentBalance(walletDto.getCurrentBalance());
        final Wallet updatedWallet = walletRepository.save(wallet);
        System.out.println("Updated User " + updatedWallet);
        return walletRepository.save(updatedWallet);
    }

//    @GetMapping("/wallet/user/{userId")
//    @PreAuthorize("hasRole('ADMIN')")
//        public ResponseEntity<Wallet> getWalletByUserId(@PathVariable(value = "userId") Long userId) throws ResourceNotFoundException {
//        Wallet wallet = walletRepository.findByUserId(userId);
//        if (wallet == null) {
//            throw new ResourceNotFoundException("Wallet not found for this id: " + userId);
//        }
//        return ResponseEntity.ok().body(wallet);
//    }



    //delete user
//    @DeleteMapping("wallet/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
//    public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long id)
//            throws ResourceNotFoundException {
//        Wallet wallet = walletRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Wallet not found for this id: " + id));
//        walletRepository.delete(wallet);
//        Map<String, Boolean> response = new HashMap<>();
//        response.put("deleted", Boolean.TRUE);
//        return response;
//    }


}
