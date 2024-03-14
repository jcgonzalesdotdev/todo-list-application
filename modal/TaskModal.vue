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
    <div v-if="modalActive" class="modal-container">
        <div  class="modal-subcontainer">
            <slot />
        </div>
    </div>
</template>