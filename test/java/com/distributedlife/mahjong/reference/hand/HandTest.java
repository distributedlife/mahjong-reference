package com.distributedlife.mahjong.reference.hand;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class HandTest {
    @Test
    public void canConvertHandIntoBitField() {
        List<String> requiredTiles = Arrays.asList(
                "1 Bamboo", "1 Bamboo", "1 Bamboo", "1 Bamboo",
                "2 Bamboo", "2 Bamboo", "2 Bamboo",
                "3 Bamboo", "4 Bamboo", "5 Bamboo", "6 Bamboo", "7 Bamboo", "8 Bamboo", "9 Bamboo"
        );

        Hand hand = new Hand("run, pung and a pair", requiredTiles);

        assertThat(hand.getPart1(), is(511L));
        assertThat(hand.getPart2(), is(3L));
        assertThat(hand.getPart3(), is(3L));
        assertThat(hand.getPart4(), is(1L));
    }

    @Test
    public void canCompareHandsForPartialMatches() {
        List<String> requiredTiles = Arrays.asList(
                "1 Bamboo", "1 Bamboo", "1 Bamboo", "1 Bamboo",
                "2 Bamboo", "2 Bamboo", "2 Bamboo",
                "3 Bamboo", "4 Bamboo", "5 Bamboo", "6 Bamboo", "7 Bamboo", "8 Bamboo", "9 Bamboo"
        );

        Hand hand = new Hand("run, pung and a pair", requiredTiles);
        Hand partialMatch = new Hand("run, pung and a pair", Arrays.asList("1 Bamboo", "1 Crack"));

        assertTrue(hand.isPartialMatch(partialMatch));
    }

    @Test
    public void canHandleExactMatches() {
        List<String> requiredTiles = Arrays.asList(
                "1 Bamboo", "1 Bamboo", "1 Bamboo", "1 Bamboo",
                "2 Bamboo", "2 Bamboo", "2 Bamboo",
                "3 Bamboo", "4 Bamboo", "5 Bamboo", "6 Bamboo", "7 Bamboo", "8 Bamboo", "9 Bamboo"
        );

        Hand hand = new Hand("run, pung and a pair", requiredTiles);
        Hand partialMatch = new Hand("run, pung and a pair", requiredTiles);

        assertTrue(hand.isPartialMatch(partialMatch));
    }

    @Test
    public void canHandleNoMatch() {
        List<String> requiredTiles = Arrays.asList(
                "1 Bamboo", "1 Bamboo", "1 Bamboo", "1 Bamboo",
                "2 Bamboo", "2 Bamboo", "2 Bamboo",
                "3 Bamboo", "4 Bamboo", "5 Bamboo", "6 Bamboo", "7 Bamboo", "8 Bamboo", "9 Bamboo"
        );
        List<String> differentTiles = Arrays.asList(
                "1 Crack", "1 Crack", "1 Crack", "1 Crack",
                "2 Crack", "2 Crack", "2 Crack",
                "3 Crack", "4 Crack", "5 Crack", "6 Crack", "7 Crack", "8 Crack", "9 Crack"
        );

        Hand hand = new Hand("run, pung and a pair", requiredTiles);
        Hand partialMatch = new Hand("run, pung and a pair", differentTiles);

        assertFalse(hand.isPartialMatch(partialMatch));
    }
}
