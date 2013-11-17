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

## Create a Role

Postgres uses the concept of roles to distinguish the variety of users that can connect to a database. When it is first installed on a server, the default postgres user is actual named “postgres”.

The postgres default is to use ident authentication, tying each server user to a Postgres account.

```bash
su - postgres
```

```bash
createuser
```

```bash
Enter name of role to add: anthony
Shall the new role be a superuser? (y/n) y
```

Add password to user:
```bash
createuser --pwprompt
```

## List Roles

```bash
psql
SELECT rolname FROM pg_roles;
```

## References
1. https://help.ubuntu.com/community/PostgreSQL
2. http://ubuntuforums.org/showthread.php?t=1789374
3. https://www.digitalocean.com/community/articles/how-to-install-and-use-postgresql-on-ubuntu-12-04