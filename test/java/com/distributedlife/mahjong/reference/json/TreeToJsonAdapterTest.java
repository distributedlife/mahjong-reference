package com.distributedlife.mahjong.reference.json;

import com.distributedlife.mahjong.reference.node.HandNode;
import org.json.JSONObject;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TreeToJsonAdapterTest {
    @Test
    public void shouldConvertANodeToJson() {
        HandNode node = new HandNode("derp");

        TreeToJsonAdapter converter = new TreeToJsonAdapter();
        JSONObject json = converter.convert(node);

        assertThat(json.toString(), is("{\"name\":\"derp\"}"));
    }

    @Test
    public void shouldNestNodes() {
        HandNode node = new HandNode("derp");
        node.addChild("child");
        HandNode child = node.getChild("child");
        child.addChild("grandchild");

        TreeToJsonAdapter converter = new TreeToJsonAdapter();
        JSONObject json = converter.convert(node);

        assertThat(json.toString(), is("{\"name\":\"derp\",\"children\":[{\"name\":\"child\",\"children\":[{\"name\":\"grandchild\"}]}]}"));
    }

    @Test
    public void shouldHaveChildrenAsSiblings() {
        HandNode node = new HandNode("derp");
        node.addChild("child1");
        node.addChild("child2");

        TreeToJsonAdapter converter = new TreeToJsonAdapter();
        JSONObject json = converter.convert(node);

        assertThat(json.toString(), is("{\"name\":\"derp\",\"children\":[{\"name\":\"child1\"},{\"name\":\"child2\"}]}"));
    }

    @Test
    public void shouldNotPrintChildrenIfThereAreNone() {
        HandNode node = new HandNode("derp");

        TreeToJsonAdapter converter = new TreeToJsonAdapter();
        JSONObject json = converter.convert(node);

        assertThat(json.toString(), is("{\"name\":\"derp\"}"));
    }
}