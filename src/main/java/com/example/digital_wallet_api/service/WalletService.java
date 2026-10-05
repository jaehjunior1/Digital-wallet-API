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

    public Wallet updateWallet(Long id, Wallet walletDetails) {

        Wallet wallet = walletRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Wallet with ID " + id + " not found"));

        wallet.setAccountName(walletDetails.getAccountName());
        wallet.setAccountNumber(walletDetails.getAccountNumber());
        wallet.setEmail(walletDetails.getEmail());
        wallet.setBalance(walletDetails.getBalance());
        

        return walletRepository.save(wallet);
    }

    public void deleteWallet(Long id) {

        Wallet wallet = walletRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Wallet with ID " + id + " not found"));

        walletRepository.delete(wallet);
    }
}