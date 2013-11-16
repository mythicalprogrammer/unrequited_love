## Installation

### Ubuntu

```bash
  sudo apt-get update
  sudo apt-get install postgresql postgresql-contrib
```

### Login postgres user

```bash
  su - postgres
```

If you can't login, change the user password.

```bash
  sudo passwd postgres
```

To change the postgres role, a role confusingly named postgres (same as the username postgres on your linux machine) in postgres db.

```bash
sudo -u postgres psql postgres
\password postgres
```

## References
1. https://help.ubuntu.com/community/PostgreSQL
2. http://ubuntuforums.org/showthread.php?t=1789374
3. https://www.digitalocean.com/community/articles/how-to-install-and-use-postgresql-on-ubuntu-12-04