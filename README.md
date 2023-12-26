## Kathmandu Valley Bus Fare Calculating System 

### Overview
This project calculates the fare in kathmandu valley using the distances between the stops and discount Factor.

### Features:
1. CRUD Operations on:
   - Bus Route: Bus Route contains all the necessary elements like path location array, name of the Bus Route. And its crud operations are handled well.
   - Bus Stop: Bus Stop has one location and a name. The fields are handled well.
2. Fare Calculation Engine:
   - It gets the bus stops and bus route
   - Calculates the distance between bus stop using the BusRoute
   - Considers discount aspect of the travelling
3. Automatic update on Bus Route and Bus Stop:
   - When new Bus Stop is added on the path of Bus Route or vice versa, the Bus Stop and Bus Route are automatically updated in database.
