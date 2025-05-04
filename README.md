# JOIN Society

## Table of Contents
* [Overview](#overview)
* [Prerequisites](#prerequisites)
* [Backend Setup](#backend-setup)
* [Frontend Setup](#frontend-setup)
* [Contributors](#contributors)

## Overview
JOIN Society is an e-commerce platform for selling high quality, trendy clothing. This repository contains the backend built with Spring Boot and the frontend built with React.js. A MySQL database is managed using Docker.

## Prerequisites
Before running the project, ensure you have the following installed:
* [Docker](https://docs.docker.com/get-started/get-docker/)
* [Java Development Kit (JDK) 17](https://www.oracle.com/java/technologies/downloads/#java17)
* [Node.js v23](https://nodejs.org/en/download)

## Backend Setup
* Export the project root folder as an environment variable.
  ```sh
  export PROJECT_ROOT=$(pwd)
  ```
* Navigate to the mentioned directory.
  ```sh
  cd ${PROJECT_ROOT}/join-society/join-society
  ```
* Create a `.env` file. You can do this by copying the example file given.
  ```sh
  cp .env.example .env
  ```
* Create the database container using docker.
  ```sh
  docker-compose up --build -d
  ```
* Install the package `dotenv-cli` using `npm`. This will be required to configure the Springboot application with environment variables.
  ```sh
  npm install -g dotenv-cli
  ```
* Install the dependencies for the Springboot application.
  ```sh
  ./mvnw clean install
  ```
* Run the application server.
  ```sh
  dotenv -e .env ./mvnw spring-boot:run
  ```
  The application server should be running on port `8080`.

## Frontend Setup
* Navigate to the mentioned directory
  ```sh
  cd ${PROJECT_ROOT}/join-society-website
  ```
* Install the dependencies.
  ```sh
  npm install
  ```
* Run the client server.
  ```
  npm start
  ```
  The client server should be running on port `5173`. Open your browser and visit [http://localhost:5173](http://localhost:5173).

## Contributors
* Mohammed Misran - [mohammed.misran@pitt.edu](mailto:mohammed.misran@pitt.edu)
* Satvik Tandon - [satvik.tandon@pitt.edu](mailto:satvik.tandon@pitt.edu)
* Varun Shelke - [varun.shelke@pitt.edu](mailto:varun.shelke@pitt.edu)
