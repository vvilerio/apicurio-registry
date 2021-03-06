#!/bin/bash
set -euxo pipefail

# Initializing the variable with the Passed Parameters
BRANCH_REF="$1"

# Removing string "refs/heads" from variable ${BRANCH_REF} using Parameter Substitution
BRANCH_NAME=${BRANCH_REF#refs/heads/}


case $BRANCH_NAME in

  "master")
       echo "Tagging Snapshot Images for Branch '$BRANCH_NAME'"
       docker image tag apicurio/apicurio-registry-mem:latest-snapshot quay.io/apicurio/apicurio-registry-mem:latest-snapshot
       docker image tag apicurio/apicurio-registry-infinispan:latest-snapshot quay.io/apicurio/apicurio-registry-infinispan:latest-snapshot
       docker image tag apicurio/apicurio-registry-kafkasql:latest-snapshot quay.io/apicurio/apicurio-registry-kafkasql:latest-snapshot
       docker image tag apicurio/apicurio-registry-sql:latest-snapshot quay.io/apicurio/apicurio-registry-sql:latest-snapshot
       docker image tag apicurio/apicurio-registry-streams:latest-snapshot quay.io/apicurio/apicurio-registry-streams:latest-snapshot
       docker image tag apicurio/apicurio-registry-tenant-manager-api:latest-snapshot quay.io/apicurio/apicurio-registry-tenant-manager-api:latest-snapshot
       ;;

   *)
       echo "Tagging Snapshot Images for Branch '$BRANCH_NAME'"
       docker image tag apicurio/apicurio-registry-mem:${BRANCH_NAME}-snapshot quay.io/apicurio/apicurio-registry-mem:${BRANCH_NAME}-snapshot
       docker image tag apicurio/apicurio-registry-infinispan:${BRANCH_NAME}-snapshot quay.io/apicurio/apicurio-registry-infinispan:${BRANCH_NAME}-snapshot
       docker image tag apicurio/apicurio-registry-kafkasql:${BRANCH_NAME}-snapshot quay.io/apicurio/apicurio-registry-kafkasql:${BRANCH_NAME}-snapshot
       docker image tag apicurio/apicurio-registry-sql:${BRANCH_NAME}-snapshot quay.io/apicurio/apicurio-registry-sql:${BRANCH_NAME}-snapshot
       docker image tag apicurio/apicurio-registry-streams:${BRANCH_NAME}-snapshot quay.io/apicurio/apicurio-registry-streams:${BRANCH_NAME}-snapshot
       docker image tag apicurio/apicurio-registry-tenant-manager-api:${BRANCH_NAME}-snapshot quay.io/apicurio/apicurio-registry-tenant-manager-api:${BRANCH_NAME}-snapshot
       ;;

esac
        