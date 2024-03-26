<script setup>

defineProps({
    modalActive: {
        type: Boolean,
        default: false,
    },
})
</script>
<template>
        <div v-if="modalActive" class="modal">
            <div class="modal-container">
                <div class="modal-content">
                    <slot />
                </div>
            </div>
        </div>
</template>

<style scoped>
/* Styles for the modal */
.modal {
  display: block;
  position: fixed;
  z-index: 1;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  overflow: hidden;
  background-color: rgba(0, 0, 0, 0.4);
}

.modal-container {
  display: flex;
  justify-content: center;
  align-items: center; 
  height: 100vh; 
  margin: 0; 
}

.modal-content {
  background-color: #fefefe;
  margin: 5% auto;
  padding: 20px;
  border: 1px solid #888;
  width: 20%;
  color: black;
}

</style>