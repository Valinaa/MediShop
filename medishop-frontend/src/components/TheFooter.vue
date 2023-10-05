<template>
    <div class="text-center container">
        <p class="color-text">
            &copy; 2023
            <b>www.valinaa-wei.tech</b>
        </p>
        <br />
        <p class="color-text">All rights reserved.</p>
    </div>
</template>

<style lang="scss" scoped>
.container {
    margin: 0 auto;
}

.color-text {
    display: inline-block;
    margin: auto;
    font-size: large;
    font-family: 'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande',
        'Lucida Sans', 'Arial', sans-serif;
    background-image: linear-gradient(
        272deg,
        #092158,
        #124983,
        #1273b0,
        #00a1df
    );
    background-clip: text;
    -webkit-text-fill-color: transparent;
    /* stylelint-disable-next-line property-no-unknown */
    text-fill-color: transparent;
}

.dark .color-text {
    display: inline-block;
    font-size: large;
    font-family: 'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande',
        'Lucida Sans', 'Arial', sans-serif;
    background-image: linear-gradient(
        92deg,
        #fa0000,
        #dc435e,
        #ac5eaa,
        #0074fa
    );
    background-clip: text;
    -webkit-text-fill-color: transparent;
    /* stylelint-disable-next-line property-no-unknown */
    text-fill-color: transparent;
}
</style>
