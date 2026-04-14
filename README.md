# 🔐 Java CLI Password Manager

A secure, local, command-line password manager built in Java.
This project demonstrates core concepts of **authentication, encryption, and secure storage** using modern cryptographic practices.

---

## 🚀 Features

* 🔐 **Master Password Authentication**

  * SHA-256 hashing for secure verification
  * No plaintext password storage

* 🔑 **AES-GCM Encryption**

  * Authenticated encryption using `AES/GCM/NoPadding`
  * Random IV generated per entry
  * Ensures both confidentiality and integrity

* 📁 **Multi-Vault Support**

  * Each vault stored as a separate file
  * Easy creation and management of multiple vaults

* 🧠 **In-Memory + Persistent Storage**

  * Entries loaded into memory on unlock
  * Persisted securely to disk

* 📦 **Base64 Encoding**

  * Binary encrypted data safely stored as text

---

## 🧠 How It Works

### 🔐 Authentication Flow

1. User creates a vault → sets master password
2. Password is hashed using SHA-256
3. Only the hash is stored in the vault file

During login:

```
input password → hash → compare with stored hash
```

---

### 🔑 Encryption Flow

* AES key is derived from the master password (via SHA-256)
* Each entry is encrypted using AES-GCM
* A random IV is generated per encryption
* IV + ciphertext are combined and Base64 encoded

#### Save Flow:

```
Entry → String → AES Encrypt → Base64 → File
```

#### Load Flow:

```
File → Base64 Decode → AES Decrypt → Entry Object
```

---

## 📂 Project Structure

```
.
├── Encryption.java       # AES-GCM encryption/decryption
├── Hashing.java          # SHA-256 hashing
├── Vault.java            # Core vault logic (auth + storage)
├── Entry.java            # Data model
├── PasswordManager.java  # CLI interface
└── vaults/               # Stores encrypted vault files
```

---

## 🛠️ Technologies Used

* Java (Core)
* `javax.crypto` (AES-GCM)
* `java.security` (SHA-256, SecureRandom)
* Base64 Encoding

---

## ▶️ How to Run

### 1. Clone the repo

```
git clone https://www.github.com/Adityanath1221/SecureVault.git
cd SecureVault
```

### 2. Compile

```
javac src/*.java
```

### 3. Run

```
java -cp src PasswordManager
```

---

## 🧪 Example Usage

```
===PASSWORD MANAGER===
1 → Create new vault
2 → Open vault
3 → Add entry
4 → List entries
5 → Close vault
-1 → Exit
```

---

## 🔒 Security Notes

This project is built for **learning purposes** and demonstrates practical cryptographic concepts.

### ✅ Implemented

* AES-GCM (modern authenticated encryption)
* Random IV per encryption
* SHA-256 password hashing
* No plaintext credential storage

### ⚠️ Limitations (Intentional)

* No salting or PBKDF2 (key derivation)
* Master password stored in memory during session
* No protection against brute-force attacks

---

## 💡 Future Improvements

* PBKDF2 / Argon2 for stronger key derivation
* Password masking & clipboard copy
* Search (`get <site>`)
* Update & delete entries
* GUI / Web interface
* Auto-lock after inactivity

---

## 🧠 What This Project Demonstrates

* Secure system design principles
* Authentication vs encryption separation
* Data persistence and reconstruction
* CLI-based application architecture
* Practical use of cryptography in real applications

---

## 👨‍💻 Author

Built by Adi 🚀
2nd Year B.Tech CSE Student

---

## ⭐ Final Note

This project is not just about storing passwords —
it’s about understanding how **secure systems are designed from scratch**.
