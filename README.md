(Check master branch)

# Architecture (MVVM), Why?

  - Lists all the non-ui logic / functions to View Model class in able to conduct a proper ui and unit test (except some deprecated or not updated libraries or functions)
  - Allows re-usability of both repository and viewmodel on other classes with similar approach and needs similar functionalities
  - Enable us to directly link the UI to the Viewmodel for functionalities, action listeners and observables. This gets ret of clutters caused by handling UI changes on the activities / fragments
  - Generally, allows us to inject all the functionalities aforementioned above as a single re-usable instance.

# Room, How?

- Since this is the first time I used room, how did i manage to merge it to my code style?
--Reused the pojo data classes being used in api service as return classes into an entity as well for the entire ROOM schema
--Since the app I created is fairly small in functions, and serve its purpose as a display of code capability only, I used room to save previously viewed / searched tracks and lists it down the next time the user opens the app, instead of a blank screen. Why? (See below)
--This is my way of showing how to eliminate some undesired user experience (e.a. blank screen) via the use of existing functionalities we have (room).

# PROS

- Clean architecture with the use of latest technologies (MVVM, Dagger Injection, RXJava / RXAndroid, Okhttp, Glide, etc)
- Majority of the classes / functions in this approach, can be re-used and are independent of the UI, meaning UI and UX can be tested
- Time constraint (I am under the weather with flu, I only had several hours to plan the archi), generally not an excuse
 

# CONS

- No connectivity handle
- Unhandled other scenarios, fail path
- Highly for display of code ethics only, not desirable UI - Functionalities combination

# WHAT CAN WE DO?

- Can explain shortcomings through an interview.


# Your thought?
