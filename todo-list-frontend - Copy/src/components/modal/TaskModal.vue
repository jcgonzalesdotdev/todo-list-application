<script setup>
// import { DefineProps } from 'vue';

defineProps({
    modalActive: {
        type: Boolean,
        default: false,
    },
})
</script>
<template>
    <div v-show="modalActive" class="modal-container">
        <div v-if="modalActive" class="modal-subcontainer">
            <slot />
            <button>Close</button>
        </div>
    </div>
</template>
<style>

.modal-container {
    position: absolute;
    width: 100%;
    height: 10vh;
    background-color: blanchedalmond;
    top: 0;
    display: flex;
    justify-content: center;
}
button{
    background-color: blue;
    color: aliceblue;
}
</style>