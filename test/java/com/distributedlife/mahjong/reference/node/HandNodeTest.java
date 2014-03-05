package com.distributedlife.mahjong.reference.node;

import com.distributedlife.mahjong.reference.hand.Hand;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class HandNodeTest {
    @Test
    public void shouldBeAbleToSearchChildrenForNodes() {
        HandNode handNode = new HandNode("Margery");

        assertFalse(handNode.hasChild("Edward"));

        handNode.addChild("Edward");

        assertTrue(handNode.hasChild("Edward"));
    }

    @Test
    public void shouldBeAbleToGetChildren() {
        HandNode handNode = new HandNode("Margery");
        handNode.addChild("Edward");

        assertThat(handNode.getChild("Edward").getName(), is("Edward"));
    }
}
