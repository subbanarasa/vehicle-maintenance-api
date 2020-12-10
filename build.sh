#!/usr/bin/env bash

mvn clean package -DskipTests

buildImage -i vehicle-maintenance
