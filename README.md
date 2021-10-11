# PizzaClub
The restaurant chain Pizza Cabin Inc. (PCI) has grown a lot recently. Now they are so big that they have some 850 people, known as Pizza Experts, taking pizza orders via phone. To create optimal schedules for their Pizza Experts they use a state-of-the-art workforce management (WFM) system. This system takes into account the demand for service and schedule start, end, break and lunch times accordingly.
Having seen the benefits of the WFM system and the flexible schedules, other systems are also looking for the schedule data to visualize and query. The product owners of the other internal systems have come to the conclusion that the easiest way ahead would be to get the schedule details into a custom database to be able query them and take it from there.
The WFM system does not have this functionality out of the box but needs help from a developer resource to build an integration to get the data from the REST service (json output) that the WFM service exposes and store it into a database.
To find a solution to this, PCI has decided to engage one lonely consultant (guess who?). As a first step PCI wants a basic integration that pulls the json object returned from the REST service and transforms it into a queryable database for other systems to access.

*) See http://pizzacabininc.azurewebsites.net/PizzaCabinInc.svc/schedule/2015-12-14 for an example.
