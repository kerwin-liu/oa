#!/bin/bash
this="${BASH_SOURCE-$0}"
bin=`dirname "$0"`
bin=`cd "$bin"; pwd`
APP_HOME=`dirname "$bin"`

cd $APP_HOME
git fetch --all  
git reset --hard origin/master 
git pull && mvn clean package && echo 'done'