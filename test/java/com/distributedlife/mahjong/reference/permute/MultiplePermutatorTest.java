package com.distributedlife.mahjong.reference.permute;

import com.distributedlife.mahjong.reference.hand.HandCandidate;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class MultiplePermutatorTest {
    @Test
    public void shouldCreateANewPermutationForACandidateInTheAvailableTilesWithEnoughCopiesOfTheTile() {
        HandCandidate candidate = new HandCandidate("candidate", Arrays.asList("1 Bamboo", "1 Bamboo"));
        candidate.setPrimarySuit("Bamboo");
        List<HandCandidate> inCandidates = Arrays.asList(candidate);

        int copies = 2;
        MultiplePermutator multiplePermutator = new MultiplePermutator(Arrays.asList("1"), copies, "1st");
        List<HandCandidate> outCandidates = multiplePermutator.permute(inCandidates);

        assertThat(outCandidates.size(), is(1));
        assertThat(outCandidates.get(0).getRequiredTiles(), is(Arrays.asList("1 Bamboo", "1 Bamboo")));
    }

    @Test
    public void shouldNotCreateANewPermutationIfThereAreNotEnoughTilesAvailable() {
        HandCandidate candidate = new HandCandidate("candidate", Arrays.asList("1 Bamboo", "1 Bamboo"));
        candidate.setPrimarySuit("Bamboo");
        List<HandCandidate> inCandidates = Arrays.asList(candidate);

        int copies = 3;
        MultiplePermutator multiplePermutator = new MultiplePermutator(Arrays.asList("1"), copies, "1st");
        List<HandCandidate> outCandidates = multiplePermutator.permute(inCandidates);

        assertThat(outCandidates.size(), is(0));
    }

    @Test
    public void shouldNotCreateANewPermutationIfThereAreNotEnoughTilesAvailableForTheSuit() {
        HandCandidate candidate = new HandCandidate("candidate", Arrays.asList("1 Bamboo", "1 Bamboo"));
        candidate.setPrimarySuit("Spot");
        List<HandCandidate> inCandidates = Arrays.asList(candidate);

        int copies = 1;
        MultiplePermutator multiplePermutator = new MultiplePermutator(Arrays.asList("1"), copies, "1st");
        List<HandCandidate> outCandidates = multiplePermutator.permute(inCandidates);

        assertThat(outCandidates.size(), is(0));
    }

    @Test
    public void shouldCreateNewCandidatesForEverySuitAllowed() {
        HandCandidate candidate1 = new HandCandidate("candidate1", Arrays.asList("1 Bamboo", "1 Bamboo"));
        candidate1.setPrimarySuit("Bamboo");
        HandCandidate candidate2 = new HandCandidate("candidate2", Arrays.asList("1 Spot", "1 Spot"));
        candidate2.setPrimarySuit("Spot");
        List<HandCandidate> inCandidates = Arrays.asList(candidate1, candidate2);

        int copies = 2;
        MultiplePermutator multiplePermutator = new MultiplePermutator(Arrays.asList("1"), copies, "1st");
        List<HandCandidate> outCandidates = multiplePermutator.permute(inCandidates);

        assertThat(outCandidates.size(), is(2));
        assertThat(outCandidates.get(0).getRequiredTiles(), is(Arrays.asList("1 Bamboo", "1 Bamboo")));
        assertThat(outCandidates.get(1).getRequiredTiles(), is(Arrays.asList("1 Spot", "1 Spot")));
    }

    @Test
    public void shouldCreateNewCandidatesForEveryInputCandidate() {
        HandCandidate candidate1 = new HandCandidate("candidate1", Arrays.asList("1 Bamboo", "1 Bamboo"));
        candidate1.setPrimarySuit("Bamboo");
        HandCandidate candidate2 = new HandCandidate("candidate2", Arrays.asList("1 Bamboo", "1 Bamboo"));
        candidate2.setPrimarySuit("Bamboo");
        List<HandCandidate> inCandidates = Arrays.asList(candidate1, candidate2);

        int copies = 2;
        MultiplePermutator multiplePermutator = new MultiplePermutator(Arrays.asList("1"), copies, "1st");
        List<HandCandidate> outCandidates = multiplePermutator.permute(inCandidates);

        assertThat(outCandidates.size(), is(2));
        assertThat(outCandidates.get(0).getRequiredTiles(), is(Arrays.asList("1 Bamboo", "1 Bamboo")));
        assertThat(outCandidates.get(1).getRequiredTiles(), is(Arrays.asList("1 Bamboo", "1 Bamboo")));
    }


    @Test
    public void shouldPermuteOnEachTileItIsAllowedToPermuteOn() {
        HandCandidate candidate = new HandCandidate("candidate", Arrays.asList("1 Bamboo", "1 Bamboo", "3 Bamboo", "3 Bamboo"));
        candidate.setPrimarySuit("Bamboo");
        List<HandCandidate> inCandidates = Arrays.asList(candidate);

        int copies = 2;
        MultiplePermutator multiplePermutator = new MultiplePermutator(Arrays.asList("1", "3"), copies, "1st");
        List<HandCandidate> outCandidates = multiplePermutator.permute(inCandidates);

        assertThat(outCandidates.size(), is(2));
        assertThat(outCandidates.get(0).getRequiredTiles(), is(Arrays.asList("1 Bamboo", "1 Bamboo")));
        assertThat(outCandidates.get(1).getRequiredTiles(), is(Arrays.asList("3 Bamboo", "3 Bamboo")));
    }

    @Test
    public void shouldBuildTheTileInTheCandidatesSecondSuitIfRequired() {
        HandCandidate candidate = new HandCandidate("candidate", Arrays.asList("1 Bamboo", "1 Spot"));
        candidate.setPrimarySuit("Bamboo");
        candidate.setSecondSuit("Spot");
        List<HandCandidate> inCandidates = Arrays.asList(candidate);

        int copies = 1;
        MultiplePermutator multiplePermutator = new MultiplePermutator(Arrays.asList("1"), copies, "2nd");
        List<HandCandidate> outCandidates = multiplePermutator.permute(inCandidates);

        assertThat(outCandidates.size(), is(1));
        assertThat(outCandidates.get(0).getRequiredTiles(), is(Arrays.asList("1 Spot")));
    }

    @Test
    public void shouldBuildTheTileInTheCandidatesThirdSuitIfRequired() {
        HandCandidate candidate = new HandCandidate("candidate", Arrays.asList("1 Bamboo", "1 Spot", "1 Crack"));
        candidate.setPrimarySuit("Bamboo");
        candidate.setSecondSuit("Spot");
        candidate.setThirdSuit("Crack");
        List<HandCandidate> inCandidates = Arrays.asList(candidate);

        int copies = 1;
        MultiplePermutator multiplePermutator = new MultiplePermutator(Arrays.asList("1"), copies, "3rd");
        List<HandCandidate> outCandidates = multiplePermutator.permute(inCandidates);

        assertThat(outCandidates.size(), is(1));
        assertThat(outCandidates.get(0).getRequiredTiles(), is(Arrays.asList("1 Crack")));
    }
}
