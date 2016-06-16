# Fashion-gallery
## Web project to demonstrate JSF, REST and MongoDB  

###Requirments
* Create a backend with persistence and CRUD.
* Include several EE/Spring frameworks
* Include a ftrondend to communicate with CRUD commands.
* Preferably code some with a friend, on both's projects

###Project plan/layout
A fashion-gallery webpage, where the user can use a filter-form to choose what the gallery should show. The items at the moment are different clothing items from different web-shops, such as Esprit or Scotch & Soda. Preferably the user can have a login to store/show personal favorites. Items should also be editable and deletable. User should also be able to add items.

###Techniques/frameworks

![project structure](https://github.com/hnyberg/Fashion-gallery/blob/master/Classes.jpg)

* For front end, I've chosen Java Server Faces, since it works well for forms (such as filter for search preferences). Everything is managed on index.xhtml, and a managed bean works as the controller.  
* The bean is built with CDI and EJB, and communicates with a RESTful API build with JAX-RS.  
* The API then talks to a Mongo DB through a DAO, using a mongo-java-driver -jar for JDBC.

*(Login/session logic and REST-API is added last, while the JSF-frontend and Mongo-DAO-backend are first priority.)*

###Instructions for applying app
To run this, you need mongoDB installed to your system. You also need some application server that can run EJB, like GlassFish.
Before you start the project, you need to import the database. After you've installed mongoDB into your system, open the command prompt, navigate to the project folder where the fashon.json lies, and import the database to your local mongoDB by writing:  

    "mongoimport --db fashion --collection items --file fashion.json". 

For now, fork and clone the repo, open it preferably in Eclipse, and run index.xhtml on the app-server. Then play around with the webpage to filter your gallery. There are only 40 items in the database atm, so preferably mark every filter button to ensure large enough filter result.

###Resources
[MongoDB Java Driver instructions](https://docs.mongodb.com/getting-started/java/introduction/)  
