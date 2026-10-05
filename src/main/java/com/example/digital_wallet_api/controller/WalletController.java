package com.example.digital_wallet_api.controller;

import com.example.digital_wallet_api.entity.Wallet;
import com.example.digital_wallet_api.service.WalletService;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wallets")
public class WalletController {

    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    // Create wallet
    @PostMapping
    public ResponseEntity<Wallet> createWallet(
            @Valid @RequestBody Wallet wallet) {

        Wallet createdWallet = walletService.createWallet(wallet);

        return new ResponseEntity<>(
                createdWallet,
                HttpStatus.CREATED
        );
    }

    // Get all wallets
    @GetMapping
    public ResponseEntity<List<Wallet>> getAllWallets() {

        return ResponseEntity.ok(
                walletService.getAllWallets()
        );
    }

    // Get wallet by ID
    @GetMapping("/{id}")
    public ResponseEntity<Wallet> getWalletById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                walletService.getWalletById(id)
        );
    }

    // Update wallet
    @PutMapping("/{id}")
    public ResponseEntity<Wallet> updateWallet(
            @PathVariable Long id,
            @Valid @RequestBody Wallet walletDetails) {

        Wallet updatedWallet =
                walletService.updateWallet(id, walletDetails);

        return ResponseEntity.ok(updatedWallet);
    }

    // Delete wallet
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteWallet(
            @PathVariable Long id) {

        walletService.deleteWallet(id);

        return ResponseEntity.ok(
                "Wallet with ID " + id + " deleted successfully"
        );
    }
}