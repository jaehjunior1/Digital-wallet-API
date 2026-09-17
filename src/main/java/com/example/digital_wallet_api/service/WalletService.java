package com.example.digital_wallet_api.service;

import com.example.digital_wallet_api.entity.Wallet;
import com.example.digital_wallet_api.repository.WalletRepository;

import com.example.digital_wallet_api.exception.ResourceNotFoundException;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WalletService {

    private final WalletRepository walletRepository;

    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    // Create wallet
    public Wallet createWallet(Wallet wallet) {
        return walletRepository.save(wallet);
    }

    // Get all wallets
    public List<Wallet> getAllWallets() {
        return walletRepository.findAll();
    }

    // Get wallet by ID
    public Wallet getWalletById(Long id) {
        return walletRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Wallet with ID " + id + " not found"
                        )
                );
    }
}