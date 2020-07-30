# Music store [![CircleCI](https://circleci.com/gh/grzegorz103/music-store/tree/master.svg?style=svg)](https://circleci.com/gh/grzegorz103/music-store/tree/master)

## Table of contents
* [General info](#general-info)
* [Live demo](#live-demo)
* [Technologies](#technologies)
* [Features](#features)
* [Setup](#setup)

## General info
Online music store system which provides features such as ordering and managment music discs. 

## Live demo
**You may need to wait a while until back-end loads**    
https://music-store-2620.firebaseapp.com/

### Sample accounts

| __Username__ | __Password__ | Role |
| -------------- | ------------ | --- |
| admin | admin | Admin |
| user | user | User |  

## Technologies
- Spring (Boot, Security, Data JPA, MVC)
- Angular 8
- Hibernate
- PostgreSQL
- Mapstruct
- CircleCI

## Features
- Management discs and orders via admin panel
- Ordering discs by users
- Browse available discs for sale
- Browse user shopping cart and orders

## Setup
### Prerequisites

- Angular 8 or greater is required
```$xslt
$ npm install -g @angular/cli
``` 
- Java 8+

### Deployment

```
$ mvn spring-boot:run -Dspring-boot.run.profiles=dev
$ cd angularclient
$ npm install
$ ng serve
```
Run browser and head to ```http://localhost:4200```
