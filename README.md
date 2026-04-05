# 🔐 Password Manager CLI (Java)

A simple command-line password manager built in Java that allows users to store and manage credentials securely (locally).

## 🚀 Features (v1.0)

* Create a new vault
* Open an existing vault
* Add new entries (site, username, password)
* List all stored entries
* Close vault

## 🛠️ Tech Stack

* Java
* File Handling (FileWriter, Scanner)
* OOP Concepts

## 📂 Project Structure

```
PassManager/
│
├── src/
│   ├── PasswordManager.java
│   ├── Vault.java
│   ├── Entry.java
│
├── vaults/
```

## ▶️ How to Run

```bash
javac src/*.java
java -cp src PasswordManager
```

## ⚠️ Notes

* Data is stored in plain text (no encryption in v1.0)
* Vaults are saved as `.txt` files inside the `vaults/` directory

## 🔮 Future Improvements (v2.0)

* Search entries
* Delete entries
* Edit entries

## 🔐 Planned Features (v3.0)

* Encryption support
* Master password for vaults

---

Built as a learning project to understand Java, file handling, and system design.
