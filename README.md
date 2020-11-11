# Java-Network-FTP-Protocol

## Usage
go to bin then use the following commands
```bash
java java_network_ftp_protocol.Server
```
in an other terminal
```bash
java java_network_ftp_protocol.Client
```

Also, you may have to change the download folder
Go to Client.java and change this: 
```java
// Path to the download folder
FileOutputStream fOut = new FileOutputStream("C:\\Users\\myself\\Downloads\\"+fileName);
```
