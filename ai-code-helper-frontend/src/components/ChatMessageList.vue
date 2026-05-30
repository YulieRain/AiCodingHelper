<script setup>
import { ref, watch, nextTick } from 'vue'
import ChatMessage from './ChatMessage.vue'

const props = defineProps({
  messages: {
    type: Array,
    required: true,
  },
})

const listRef = ref(null)

watch(
  () => props.messages,
  async () => {
    await nextTick()
    if (listRef.value) {
      listRef.value.scrollTop = listRef.value.scrollHeight
    }
  },
  { deep: true },
)
</script>

<template>
  <div ref="listRef" class="message-list">
    <div class="message-container">
      <ChatMessage
        v-for="msg in messages"
        :key="msg.id"
        :role="msg.role"
        :content="msg.content"
        :timestamp="msg.timestamp"
        :loading="msg.loading"
        :streaming="msg.streaming"
      />
    </div>
  </div>
</template>

<style scoped>
.message-list {
  flex: 1;
  overflow-y: auto;
  scroll-behavior: smooth;
}

.message-container {
  max-width: var(--content-max-width);
  margin: 0 auto;
  padding: 32px 20px 20px;
}
</style>
