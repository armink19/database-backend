#!/bin/sh

# Don't continue script if any command fails.
set -e

# Create jar file.
mvn package

# Copy jar file to server.
#   This command requires that you define an ssh alias for the server
#   connection. To define the alias, add something like this to the
#   $HOME/.ssh/config file:

#   Host whaws
#     User <ACTUAL_USER>
#     HostName <ACTUAL_HOST_NAME>
#     IdentityFile <SSH_IDENTITY_FILE>

scp target/map-0.0.1-SNAPSHOT.jar whaws:/home/whmapdb/

# Restart service
ssh whaws sudo systemctl restart whmapdb
