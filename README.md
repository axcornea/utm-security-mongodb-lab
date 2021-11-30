# Security course MongoDB security lab (#7)

The main goal of this lab consists of implementing an application that stores both common and confidential data in a
MongoDB database, and proofing that only this application can access confidential data.

## Running locally

### Prerequisites

To run this application locally use need to have the following tools installed on your workstation:
* JDK 8 (OpenJDK used during development)
* Docker

### Setup

1. Start MongoDB instance

```shell
> docker compose up -d
```

2. Generate private key for data encryption and copy the output

```shell
> dd if=/dev/urandom bs=1 count=32 2>/dev/null | base64

# Example output (you can use it to test the app)
D2eSkEPt7i+98bKb7gXHr22MdgMngD5w94vXS2DJ7UI=
```

3. Configure the application to use newly generated key. Copy results of the previous command into the `mongodb.encrypt.keys[0].key` key in the `src/main/resources/application.yml` file

### Running

Run the following command inside the root of this repository:

```bash
# *nix
> ./gradlew bootRun

# Windows
> .\gradlew.bat bootRun
```

## Demo

### Results

Below is an example of application output:

```
Users from the MongoDB:
User(id=61a5c9256b0ad16479566663, name=Alice, email=alice@xcompany.com)
User(id=61a5c9256b0ad16479566664, name=Bob, email=bob@xcompany.com)
User(id=61a5c9256b0ad16479566665, name=Mike, email=mike@xcompany.com)

Meetings from the MongoDB:
Meeting(id=61a5c9256b0ad16479566666, title=Project kickoff, author=User(id=61a5c9256b0ad16479566663, name=Alice, email=alice@xcompany.com), guests=[User(id=61a5c9256b0ad16479566664, name=Bob, email=bob@xcompany.com), User(id=61a5c9256b0ad16479566665, name=Mike, email=mike@xcompany.com)])
Meeting(id=61a5c9256b0ad16479566667, title=Sprint retrospection, author=User(id=61a5c9256b0ad16479566664, name=Bob, email=bob@xcompany.com), guests=[User(id=61a5c9256b0ad16479566663, name=Alice, email=alice@xcompany.com), User(id=61a5c9256b0ad16479566664, name=Bob, email=bob@xcompany.com)])

```

You can open http://localhost/db/lab7/user (MongoDB Express) in your browser and check that `User` data is encrypted. As result, data remains encrypted at rest and applications that don't share the secret key cannot access
the sensitive data.
