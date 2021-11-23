# My Personal Project

## A Box Office System



My goal is to create a simple ticket selling system for a small
theatre company that does all their transactions via cash. In other 
words, there won't be any money transaction built into the system.

In this program, ticket sellers will be able to create 
customer profiles and "sell" them tickets to different shows. To keep it
simple, all the shows will be general admission. In addition, if you buy 
a ticket to the show you can go on any of the listed dates.

This project is of interest to me because I **love** theatre and 
I have worked in a theatre box office for a couple years. I would love a small
taste of what it is like to create the systems
that theatre companies use.


## User Stories

- As a user, I want to be able to add a customer profile
- As a user, I want to be able to add a show to the list of
shows the theatre is putting on
- As a user, I want to be able to remove a show after its
run is complete
- As a user, I want to be able to see a list of past shows
- As a user, I want to be able to show a customer a list of
upcoming shows
- As a user, I want to be able to add a customer to the patron 
list for a show
- As a user, I want to be able to view the patron list for a show
- As a user, I want to be able to show the customer what shows they
have paid to see
- As a user, I want to see what shows are on a specific date
- As a user, I want to be able to save all my box office data
- As a user, I want to be able to load all my box office data

## Phase 4: Task 2

Sat Nov 20 19:18:18 PST 2021
Set the name of the theatre to: massey


Sat Nov 20 19:18:29 PST 2021
Created a new show.


Sat Nov 20 19:18:29 PST 2021
Added a show to the theatre's upcoming show list.


Sat Nov 20 19:18:34 PST 2021
Set the title of the show to: cabaret


Sat Nov 20 19:18:37 PST 2021
Added 050402 to cabaret's dates.


Sat Nov 20 19:18:44 PST 2021
Set cabaret's ticket price to 7.0.


Sat Nov 20 19:18:51 PST 2021
Created a new patron.


Sat Nov 20 19:18:51 PST 2021
Added a patron to the theatre's patron list.


Sat Nov 20 19:18:53 PST 2021
Set patron's name to: jade


Sat Nov 20 19:18:56 PST 2021
Set patron's birthday to: 050402


Sat Nov 20 19:19:04 PST 2021
Added cabaret to jade's upcoming show list.


Sat Nov 20 19:19:04 PST 2021
Added jade to cabaret's patron list.

## Phase 4: Task 3

Right now I have a bunch of methods in the theatre class that are 
just references to methods in the ShowList and PatronList classes.
One way I would refactor to improve my design is by getting rid of
those methods and using the getter methods in the ShowList and PatronList 
classes instead. This will help clean up my code by removing unnecessary 
methods. This will also help with cohesion, making sure that each class
is focused on one thing.

In addition, to improve cohesion I would split by BoxOffice class up into
more classes so that each class has one main role. Right now the box office
class does too many things.

