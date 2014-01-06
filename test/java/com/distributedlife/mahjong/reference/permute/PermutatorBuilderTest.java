package com.distributedlife.mahjong.reference.permute;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PermutatorBuilderTest {
    private final PermutatorBuilderOptions options = mock(PermutatorBuilderOptions.class);
    private final PermutatorBuilder builder = new PermutatorBuilder();

    @Before
    public void common() {
        when(options.getSuit()).thenReturn("1st");
    }

    @Test
    public void shouldCreateAStandardSequencePermutatorWhenARunIsSuppliedWithAFromAndTo() {
        when(options.getType()).thenReturn("run");
        when(options.getFrom()).thenReturn(1);
        when(options.getTo()).thenReturn(9);
        assertThat(builder.build(options).getClass().toString(), is(StandardSequencePermutator.class.toString()));
    }
    @Test
    public void shouldCreateACombinationalSequencePermutatorWhenARunWithLengthIsSupplied() {
        when(options.getType()).thenReturn("run");
        when(options.getLength()).thenReturn(8);
        assertThat(builder.build(options).getClass().toString(), is(CombinationSequencePermutator.class.toString()));
    }

    @Test
    public void shouldCreateAPungPermutatorWhenAPungIsSupplied() {
        when(options.getType()).thenReturn("pung");
        assertThat(builder.build(options).getClass().toString(), is(PungPermutator.class.toString()));
    }

    @Test
    public void shouldCreatePairPermutatorWhenAPairIsSupplied() {
        when(options.getType()).thenReturn("pair");
        assertThat(builder.build(options).getClass().toString(), is(PairPermutator.class.toString()));
    }

    @Test
    public void shouldCreateAnyPairedPermutatorWhenAAnyPairIsSupplied() {
        when(options.getType()).thenReturn("any-paired");
        assertThat(builder.build(options).getClass().toString(), is(AnyPairedPermutator.class.toString()));
    }

    @Test
    public void shouldCreateAnySingleTilePermutatorWhenSingleIsSupplied() {
        when(options.getType()).thenReturn("single");
        assertThat(builder.build(options).getClass().toString(), is(SingleTilePermutator.class.toString()));
    }

    @Test
    public void shouldCreateAnUnknownPermutatorIfItCantWorkItOut() {
        when(options.getType()).thenReturn("derp derp derp");
        assertThat(builder.build(options).getClass().toString(), is(UnknownPermutator.class.toString()));
    }

    @Test
    public void shouldWrapThePermutatorWithASecondSuitPermutatorIfSecondSuitIsSpecified() {
        when(options.getType()).thenReturn("pung");
        when(options.getSuit()).thenReturn("2nd");
        assertThat(builder.build(options).getClass().toString(), is(SecondSuitPermutator.class.toString()));
    }

    @Test
    public void shouldCreateASubSetPermutatorWhenSubsetIsSupplied() {
        when(options.getType()).thenReturn("subset");
        when(options.getLength()).thenReturn(1);
        assertThat(builder.build(options).getClass().toString(), is(SubsetPermutator.class.toString()));
    }
}
