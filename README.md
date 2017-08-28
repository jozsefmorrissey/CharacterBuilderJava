# Staging Management System
* [Overview](#overview)
* [Setup](#setup)
* [Database](#database)
* [API](#api)

## Overview

### Mission and Scope

#### What problem does this project address?
This program is an attempt to strengthen the relationships that other programs and technologies have weaken or destroyed, between individuals an their neighbors. This program will hopefully become catalyst for face to face human interaction. The intent is to motivate individuals to engage in productive activities with individuals in their community.

#### What is the goal of this project?
The goal of this project is to introduce a generation to the benefits of a free market exchange of ideas, goods, services, etc...

#### What is the scope of this project?
The initial scope of this project is a small scale functional system that can be used to experiment, leading to modifications. With the ultimate goal of expanding to a national and even global system. Designed to collect data that can be used to supplement trust and provide opportunities. It is our belief that opportunity is the driving force behind the developed world, and that increasing the accessibility of opportunities to individuals will ultimately result in growth and prosperity for all involved.

## Setup
* [Node Web Pack Babel](#nodewebpackbabel)
* [eclipse](#eclpse)
* [Lombok](#lombok)
* [OracleDB](#oracledb)

### FrontEnd
### NodeWebpackBabel
1. Download node from https://nodejs.org/en/download/
If there is any uncertainty here is a guide to downloading it
http://blog.teamtreehouse.com/install-node-js-npm-windows

2. Open up Git Bash, the command prompt or whatever else you typically use. Note that if you had this open before installing node
    you will have to reopen it. Navigate to the directory with package.json. It should be in the root folder of the repository.

3. In your command line type: <br />
    npm install <br />
this will install all of the dependencies that have been laid out in the package.json

4. Once all of the dependencies have been installed you can have web pack watch
your code to do this type: <br />
    npm run watch

What this does is allows Webpack and Babel to bundle and transpile all of the
javascript code and it's required dependencies into one js file.
So even though you are working in your individual js files it will all be
bundled into bundle.js. Note everytime you make changes in a js file if webpack is not
watching the code it will not update the bundle.js and the changes will not be reflected.<br />
!!!!!! IF YOU DO NOT HAVE THIS RUNNING YOU WILL NOT SEE THE CHANGES YOU MAKE IN JAVASCRIPT MAKE SURE TO READ THE ECLIPSE SETUP ALSO !!!!!!!!!!!


Why is this a good thing?
Our server now only has to serve up one js file for our entire application
and Bable transpiles it into an older version of JavaScript
that runs on browsers that don't support the newest standard of JavaScript

### Eclipse
Now that you have Webpack watching your code, eclipse doesn't like to automatically refresh
So you have 2 options.
i. Everytime your js code is changed webpack will bundle it and then you have to go into eclipse
and do a manual refresh. The shortcut should be f5. This is the inefficient way.
ii. The other option is to set up eclipse to automatically detect changes.
1. In eclipse click on window -> preference. This will open up a dialogue.
2. In the search bar at the top type in workspace.
3. Click on workspace.
4. Now at the right should be a checkbox that says "Refresh using native hooks or polling"
by default this is not selected but select it and then hit ok.

### Lombok
Lombok is a tool that creates getters and setters at compile time. Maven will already include the jar when built. To get eclipse to play along use the following steps.

1. Find and open a lombok.jar either download from https://projectlombok.org/download or find the file imported by maven the file location is listed next to the jar in eclipse.

2. Run the installer

3. Shut down eclipse

4. Your eclipse shortcut should be broken. To start eclipse find and open the executable in you file system. (If you create a new shortcut it will work).

5. Enjoy no more setters and getters.



### OracleDB
#### Local Database Setup
1. Download Oracle 11g http://www.oracle.com/technetwork/database/database-technologies/express-edition/downloads/index.html
2. Ensure The Application Not Running (To avoid port conflicts make sure application is not running in eclipse)
2. Run setup, remember your password
3. Find and run the 'SQL command line' program (Cortona), execute the following commands with $PORT being a new port number like 8000.
  - connect (Connect with username: system and the password you set during installation)
  - Exec DBMS_XDB.SETHTTPPORT($PORT);

This will be the port that your oracle database will listen to.

#### Database Scripts
##### OracleDBSimpleSetup.sql
This script is a combanation of many files within 'Setup Files/DataBase/Oracle/'.

To rebuild the script, in git bash navigate to 'Setup Files/DataBase/' and run the command...

     cat OracleDB/*.sql > OracleDBSimpleSetup.sql

This script consists of the data base setup with enough data to run the SimpleTest suite in the com.characterBuilder.SpringBoot package. Use this script when making modifications to the sql scripts, to avoid the wait times associated with populating the database.

##### OracleDBPopulateData.sql
This script is a combanation of many files within 'Setup Files/DataBase/Oracle/' includeing the scripts within the PopulateData folder.

    To rebuild the script, in git bash navigate to 'Setup Files/DataBase/' and run the command...

cat OracleDB/*.sql OracleDB/PopulateData/*.sql > OracleDBPopulateData.sql

This database setup will fully populate data.


## Database

## API

## Launching the Application
