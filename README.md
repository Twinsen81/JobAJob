[![License Apache 2.0](https://img.shields.io/badge/License-Apache%202.0-blue.svg?style=true)](http://www.apache.org/licenses/LICENSE-2.0)
# JobAJob

A pet project to practise latest trends in Android software development, try out libraries and experiment with architectures.<BR/>

#### Intro

JobAJob is a fictional job search web site with RESTful API that lets employers and job seekers find each other. 
The Android client app uses the API to display and edit the data on the web site.

#### Backend

A very simple backend app has been written in Kotlin with the help of the [Spring](https://start.spring.io) framework. This will serve as a backend for the Android client app.
The app is deployed on [Heroku](https://jobajob.herokuapp.com) using a free account. The apps on free accounts are subjects to hibernation, 
so it may take up to a minute for the app to wake up on the first request. 
The app is connected to [Auth0](https://auth0.com) and requires authorization for C_UD operations, only reading is allowed for anonymous clients. 

#### Android

The app is being developed with adherence to the following principles:
 * Modularization
 * Loose coupling between modules
 * Dependency injection
 * Testability

## License

    Copyright 2019 Evgeniy Plokhov

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
