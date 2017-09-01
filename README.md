Android MVVMLib
========

A Model View ViewModel (MVVM) lib based on Android DataBinding which help you build the app faster.


Getting Started
---

Gradle:
```groovy
compile 'com.swarmnyc:mvvmlib:0.7.9'
```

MVVMLib requires at minimum Android Studio 1.3 and higher.

Deployment
---
Update publish version in mvvmlib/build.gradle
```
version = '0.7.9' // Maven Property
```

Find the BINTRAY_API_KEY in [trello](https://trello.com/).
```
export BINTRAY_API_KEY=********************************
./gradlew bintrayUpload

```

Note: to install a local version for testing run
```
./gradlew install
```

Learn more
---
* [MVVM](https://msdn.microsoft.com/en-us/library/hh848246.aspx)
* [DataBinding](https://developer.android.com/topic/libraries/data-binding/index.html)



License
=======

    Copyright 2016 SWARM, Inc.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.