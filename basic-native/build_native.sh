#!/bin/sh
./mvnw -DskipTests -Pnative native:compile
./mvnw -DskipTests -Pnative assembly:single