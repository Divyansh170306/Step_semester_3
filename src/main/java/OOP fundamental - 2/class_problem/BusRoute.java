public class BusRoute {

    private String routeCode;
    private String routeName;
    private int priority;

    public BusRoute(String routeCode, String routeName, int priority) {
        this.routeCode = routeCode;
        this.routeName = routeName;
        this.priority = priority;
    }

    public BusRoute(String routeCode, String routeName) {
        this(routeCode, routeName, 5);
    }

    public int compareTo(BusRoute other) {

        // First: priority
        if (this.priority != other.priority) {
            return this.priority - other.priority;
        }

        // Second: route code, ignoring case
        int codeResult =
                this.routeCode.compareToIgnoreCase(other.routeCode);

        if (codeResult != 0) {
            return codeResult;
        }

        // Third: route name
        return this.routeName.compareToIgnoreCase(other.routeName);
    }

    public static BusRoute[] rankRoutes(BusRoute[] routes) {

        // Bubble sort - stable
        for (int i = 0; i < routes.length - 1; i++) {

            for (int j = 0; j < routes.length - 1 - i; j++) {

                if (routes[j].compareTo(routes[j + 1]) > 0) {

                    BusRoute temp = routes[j];
                    routes[j] = routes[j + 1];
                    routes[j + 1] = temp;
                }
            }
        }

        return routes;
    }

    public String getRouteCode() {
        return routeCode;
    }

    public static void main(String[] args) {

        BusRoute[] routes = {
                new BusRoute("RT205L", "Airport Express", 3),
                new BusRoute("rt201j", "City Central", 4),
                new BusRoute("RT299T", "Night Service")
        };

        rankRoutes(routes);

        for (BusRoute route : routes) {
            System.out.println(route.getRouteCode());
        }
    }
}