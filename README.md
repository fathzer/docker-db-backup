# docker-db-backup
---
A simple way to backup your mySQL database using Docker

Work in progress ...

## Saving backups to Dropbox
--

You should get a Dropbox token using the following commands:
```
docker run -ti --entrypoint="/bin/bash" fathzer/docker-db-backup
java -cp ./jdbbackup.jar com.fathzer.jdbbackup.dropbox.DropBoxManager
```
This commands will display the following message:
>1. Go to: https://www.dropbox.com/oauth2/authorize?response_type=code&client_id=xxx
>2. Click "Allow" (you might have to log in first)
>3. Enter the authorization code there:

Open the url provided in first line with your favorite browser, authorize jdbbackup to access your Dropbox account.
Then type in the authorization code you've just got on Dropbox site and press return it will display:
>Please wait ...
>Your token is: xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
>Keep it in a secure place as it allows to access to your backup folder on Dropbox

Copy the token when type exit to close the executing container.

