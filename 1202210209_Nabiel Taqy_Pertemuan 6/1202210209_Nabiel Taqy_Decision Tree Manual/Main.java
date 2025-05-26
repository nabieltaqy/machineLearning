public class Main {
    public static void main(String[] args) {
        Tree tree = new Tree();

        Node outlook = new Node("Outlook");
        outlook.setType(NodeType.ROOTNODE);

        Node rain = new Node("Rain");
        rain.setType(NodeType.BRANCH);
        Node wind = new Node("Wind");
        wind.setType(NodeType.BRANCH);
        Node weak = new Node("Weak");
        weak.setType(NodeType.BRANCH);
        Node strong = new Node("Strong");
        strong.setType(NodeType.BRANCH);
        Node yes1 = new Node("Yes");
        yes1.setType(NodeType.LEAFNODE);
        Node no1 = new Node("No");
        no1.setType(NodeType.LEAFNODE);

        Node overcast = new Node("Overcast");
        overcast.setType(NodeType.BRANCH);
        Node yes2 = new Node("Yes");
        yes2.setType(NodeType.LEAFNODE);

        Node sunny = new Node("Sunny");
        sunny.setType(NodeType.BRANCH);
        Node humidity = new Node("Humidity");
        humidity.setType(NodeType.BRANCH);
        Node high = new Node("High");
        high.setType(NodeType.BRANCH);
        Node normal = new Node("Normal");
        normal.setType(NodeType.BRANCH);
        Node no2 = new Node("No");
        no2.setType(NodeType.LEAFNODE);
        Node yes3 = new Node("Yes");
        yes3.setType(NodeType.LEAFNODE);

        outlook.addChild(rain);
        outlook.addChild(overcast);
        outlook.addChild(sunny);

        rain.addChild(wind);
        wind.addChild(weak);
        wind.addChild(strong);
        weak.addChild(yes1);
        strong.addChild(no1);

        overcast.addChild(yes2);

        sunny.addChild(humidity);
        humidity.addChild(high);
        humidity.addChild(normal);
        high.addChild(no2);
        normal.addChild(yes3);

        tree.setRoot(outlook);
        tree.print();
    }
}
