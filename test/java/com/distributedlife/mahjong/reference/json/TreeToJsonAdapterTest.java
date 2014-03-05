package com.distributedlife.mahjong.reference.json;

import com.distributedlife.mahjong.reference.node.HandNode;
import org.json.JSONObject;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class TreeToJsonAdapterTest {
    @Test
    public void shouldConvertANodeToJson() {
        HandNode node = new HandNode("derp");

        TreeToJsonAdapter adapter = new TreeToJsonAdapter();
        JSONObject json = adapter.toJson(node);

        assertThat(json.toString(), is("{\"n\":\"derp\"}"));
    }

    @Test
    public void shouldNestNodes() {
        HandNode node = new HandNode("derp");
        node.addChild("child");
        HandNode child = node.getChild("child");
        child.addChild("grandchild");

        TreeToJsonAdapter adapter = new TreeToJsonAdapter();
        JSONObject json = adapter.toJson(node);

        assertThat(json.toString(), is("{\"c\":[{\"c\":[{\"n\":\"grandchild\"}],\"n\":\"child\"}],\"n\":\"derp\"}"));
    }

    @Test
    public void shouldHaveChildrenAsSiblings() {
        HandNode node = new HandNode("derp");
        node.addChild("child1");
        node.addChild("child2");

        TreeToJsonAdapter adapter = new TreeToJsonAdapter();
        JSONObject json = adapter.toJson(node);

        assertThat(json.toString(), is("{\"c\":[{\"n\":\"child1\"},{\"n\":\"child2\"}],\"n\":\"derp\"}"));
    }

    @Test
    public void shouldNotPrintChildrenIfThereAreNone() {
        HandNode node = new HandNode("derp");

        TreeToJsonAdapter adapter = new TreeToJsonAdapter();
        JSONObject json = adapter.toJson(node);

        assertThat(json.toString(), is("{\"n\":\"derp\"}"));
    }

    @Test
    public void shouldConvertJsonToANode() {
        JSONObject json = new JSONObject("{\"n\":\"derp\"}");

        TreeToJsonAdapter adapter = new TreeToJsonAdapter();
        HandNode node = adapter.fromJson(json);

        assertThat(node.getName(), is("derp"));
        assertThat(node.getChildren().size(), is(0));
    }

    @Test
    public void shouldNestNodesOnInflation() {
        JSONObject json = new JSONObject("{\"n\":\"derp\",\"c\":[{\"n\":\"child\",\"c\":[{\"n\":\"grandchild\"}]}]}");

        TreeToJsonAdapter adapter = new TreeToJsonAdapter();
        HandNode node = adapter.fromJson(json);

        assertTrue(node.getChild("child").hasChild("grandchild"));
    }

    @Test
    public void shouldHaveChildrenAsSiblingsOnInflation() {
        JSONObject json = new JSONObject("{\"n\":\"derp\",\"c\":[{\"n\":\"child1\"},{\"n\":\"child2\"}]}");

        TreeToJsonAdapter adapter = new TreeToJsonAdapter();
        HandNode node = adapter.fromJson(json);

        assertTrue(node.hasChild("child1"));
        assertTrue(node.hasChild("child2"));
    }

    @Test
    public void shouldReduceAndExpandTileNames() {
        HandNode node = new HandNode("North");
        node.addChild("East");
        node.addChild("West");
        node.addChild("South");
        node.addChild("Red");
        node.addChild("Green");
        node.addChild("White");
        node.addChild("Bamboo");
        node.addChild("Spot");
        node.addChild("Crack");

        TreeToJsonAdapter adapter = new TreeToJsonAdapter();
        JSONObject jsonObject = adapter.toJson(node);
        HandNode regenerated = adapter.fromJson(jsonObject);

        assertThat(regenerated.getName(), is("North"));
        assertTrue(regenerated.hasChild("East"));
        assertTrue(regenerated.hasChild("West"));
        assertTrue(regenerated.hasChild("South"));
        assertTrue(regenerated.hasChild("Red"));
        assertTrue(regenerated.hasChild("Green"));
        assertTrue(regenerated.hasChild("White"));
        assertTrue(regenerated.hasChild("Bamboo"));
        assertTrue(regenerated.hasChild("Crack"));
        assertTrue(regenerated.hasChild("Spot"));
    }
}