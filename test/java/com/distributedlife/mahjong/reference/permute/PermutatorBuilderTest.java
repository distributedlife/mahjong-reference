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
    public void shouldCreateASequencePermutatorWhenARunIsSupplied() {
        when(options.getType()).thenReturn("run");
        assertThat(builder.build(options).getClass().toString(), is(SequencePermutator.class.toString()));
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
    public void shouldCreateAnUnknownPermutatorIfItCantWorkItOut() {
        when(options.getType()).thenReturn("derp derp derp");
        assertThat(builder.build(options).getClass().toString(), is(UnknownPermutator.class.toString()));
    }

    @Test
    public void shouldWrapThePermutatorWithASecondSuitPermutatorIfSecondSuitIsSpecfied() {
        when(options.getType()).thenReturn("pung");
        when(options.getSuit()).thenReturn("2nd");
        assertThat(builder.build(options).getClass().toString(), is(SecondSuitPermutator.class.toString()));
    }
}
