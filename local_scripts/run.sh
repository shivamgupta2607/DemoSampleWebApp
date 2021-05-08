#!/usr/bin/env bash

APP_BASE_URL=http://localhost:8080

function sampleCall() {

    CMD="curl -sSX GET $APP_BASE_URL/"
    echo 'CMD='$CMD
    /bin/sh -c "$CMD"
}


function saveEmp() {
    NAME=$1
    CMD="curl -sSX POST $APP_BASE_URL -H 'content-type: application/json' -d '{\"name\":\"$NAME\"}'"
    echo 'CMD='$CMD
    /bin/sh -c "$CMD"
}

function findAllEmployees() {
    NAME=$1
    CMD="curl -sSX GET $APP_BASE_URL/all"
    echo 'CMD='$CMD
    /bin/sh -c "$CMD"
}

function findById() {
    ID=$1
    CMD="curl -sSX GET $APP_BASE_URL/$ID"
    echo 'CMD='$CMD
    /bin/sh -c "$CMD"
}

function updateNameForId() {
    ID=$1
    NEW_NAME=$2
    CMD="curl -sSX POST $APP_BASE_URL/$ID/name/$NEW_NAME"
    echo 'CMD='$CMD
    /bin/sh -c "$CMD"
}


#sampleCall

#saveEmp abcd


#findAllEmployees

findById 1


#updateNameForId 1 testing


