#!/bin/bash

# Script to clean up outdated scenarioo documentation report data.
#
# This script demonstrates how you could cleanup scenarioo reports data
# on your build system in a real project - to only keep latest needed reports.
#
# Feel free to copy this example script and adapt it to your needs in your project
# (take care to use same branch name patterns as you use in your project!).
#
# Scenarioo documentation data generated by the pizza example is cleaned up as follows:
# * remove all brannch directories that do not exist anymore
# * for remaining branch directories:
#   * Keep youngest build in any case
#   * Keep up to 5 builds younger than 5 days
#   * Keep all builds with a keep.txt file inside
#   * remove all other builds (exceeding 5 builds or older than 5 days)

# 1. Cleanup all pizza example data branch directories inside develop demo data dir:
echo "Cleanup Scenarioo Report Data of all branches for Pizza Example ..."
SCENARIOO_DATA_ROOT=/var/lib/scenarioo/data/develop
git fetch -p
if (( $? )); then
  echo "Failure: git repo not available." >&2
  exit 1
fi
AVAILABLE_BRANCHES=$(git branch -r | awk '{print pizza-delivery-$1}' | sed 's/origin\///g' | sed 's/\//\_/g' )
for BRANCH in $AVAILABLE_BRANCHES ; do
    echo "$BRANCH"
done
for BRANCH_DIR in $(find $SCENARIOO_DATA_ROOT/* -maxdepth 0 -type d) ; do
    BRANCH_NAME=$(basename $BRANCH_DIR)
    if echo "$AVAILABLE_BRANCHES" | grep -Fxq "$BRANCH_NAME"
    then
        echo "branch $BRANCH_NAME still exists - cleanup contained builds ..."
        ./ci/cleanupOutdatedScenariooBuilds.sh --dir=$BRANCH_DIR
    else
        if [[ -f "$BRANCH_DIR/branch.xml" ]] && [[ $BRANCH_NAME == *"pizza-delivery-"* ]];         then
            echo "pizza delivery branch $BRANCH_NAME not exists anymore - removing"
            rm -rf $BRANCH_DIR
        fi
    fi
done
curl http://demo.scenarioo.org/scenarioo-develop/rest/builds/updateAndImport

# 2. Cleanup outdated builds for master branch (never deleted)
# in scenarioo's master demo data dir:
# (in a real project setup you would probably use same viewer version for all your branches
#  so you could skip this special step!)
echo "Cleanup Scenarioo Report Data of master branch for Pizza Example ..."
./ci/cleanupOutdatedScenariooBuilds.sh --dir=/var/lib/scenarioo/data/master/pizza-delivery-master
curl http://demo.scenarioo.org/scenarioo-master/rest/builds/updateAndImport
