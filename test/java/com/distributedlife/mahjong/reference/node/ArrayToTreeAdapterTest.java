package com.distributedlife.mahjong.reference.node;

import com.distributedlife.mahjong.reference.hand.Hand;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class ArrayToTreeAdapterTest {
    @Test
    public void shouldConvertASingleArrayOfHandsIntoATreeWithOneNodePerElement() {
        List<String> tiles = Arrays.asList("1B", "2B", "3B");

        ArrayToTreeAdapter adapter = new ArrayToTreeAdapter();
        HandNode root = adapter.adapt(tiles);

        assertTrue(root.hasChild("1B"));
        assertTrue(root.getChild("1B").hasChild("2B"));
        assertTrue(root.getChild("1B").getChild("2B").hasChild("3B"));
    }

    @Test
    public void shouldReuseNodesWhenMultipleHandsAreAdded() {
        List<String> hand1 = Arrays.asList("1B", "2B", "3B");
        List<String> hand2 = Arrays.asList("1B", "2B", "4B");

        ArrayToTreeAdapter adapter = new ArrayToTreeAdapter();
        HandNode root = adapter.adapt(hand1);
        root = adapter.adapt(hand2, root);

        assertTrue(root.hasChild("1B"));
        assertTrue(root.getChild("1B").hasChild("2B"));
        assertTrue(root.getChild("1B").getChild("2B").hasChild("3B"));
        assertTrue(root.getChild("1B").getChild("2B").hasChild("4B"));
    }
}
