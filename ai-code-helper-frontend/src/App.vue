<script setup>
import { ref, onMounted } from 'vue'
import AnimeBackground from './components/AnimeBackground.vue'
import ChatHeader from './components/ChatHeader.vue'
import ChatMessageList from './components/ChatMessageList.vue'
import ChatInput from './components/ChatInput.vue'
import { chatStream } from './api/chat.js'
import { generateMemoryId } from './utils/memoryId.js'
import { mockMessages, createMessage } from './types/message.js'
import { useAnimeBackground } from './composables/useAnimeBackground.js'

const memoryId = ref(0)
const messages = ref([])
const isStreaming = ref(false)
const isDemo = ref(true)
let abortController = null

const { bgUrl, isLoading, isTransitioning, fetchWallpaper } = useAnimeBackground()

onMounted(() => {
  memoryId.value = generateMemoryId()
  messages.value = mockMessages.map((m) => ({ ...m }))
})

function clearConversation() {
  if (abortController) {
    abortController.abort()
    abortController = null
  }
  isStreaming.value = false
  messages.value = []
  isDemo.value = false
  memoryId.value = generateMemoryId()
}

async function sendMessage(text) {
  if (!text.trim() || isStreaming.value) return

  if (isDemo.value) {
    messages.value = []
    isDemo.value = false
  }

  messages.value.push(createMessage('user', text.trim()))

  const assistantId = createMessage('assistant', '', {
    loading: true,
    streaming: false,
  }).id

  messages.value.push({
    id: assistantId,
    role: 'assistant',
    content: '',
    timestamp: Date.now(),
    loading: true,
    streaming: true,
  })

  isStreaming.value = true
  abortController = new AbortController()

  const updateAssistant = (updater) => {
    const msg = messages.value.find((m) => m.id === assistantId)
    if (msg) updater(msg)
  }

  await chatStream(memoryId.value, text.trim(), {
    signal: abortController.signal,
    onChunk: (chunk) => {
      updateAssistant((msg) => {
        msg.loading = false
        msg.content += chunk
      })
    },
    onDone: () => {
      updateAssistant((msg) => {
        msg.loading = false
        msg.streaming = false
      })
      isStreaming.value = false
      abortController = null
    },
    onError: (err) => {
      updateAssistant((msg) => {
        msg.loading = false
        msg.streaming = false
        msg.content = msg.content || `请求失败：${err.message}`
      })
      isStreaming.value = false
      abortController = null
    },
  })
}
</script>

<template>
  <AnimeBackground :bg-url="bgUrl" :is-transitioning="isTransitioning" />
  <div class="chat-app" :class="{ 'demo-mode': isDemo }">
    <ChatHeader
      :memory-id="memoryId"
      :is-demo="isDemo"
      :bg-loading="isLoading"
      @clear="clearConversation"
      @refresh-bg="fetchWallpaper"
    />
    
    <main v-if="!isDemo" class="chat-main">
      <ChatMessageList :messages="messages" />
    </main>
    
    <div v-if="isDemo" class="demo-container">
      <div class="demo-content">
        <h1 class="demo-title">编程小助手</h1>
        <div class="demo-input-wrapper">
          <ChatInput :disabled="isStreaming" @send="sendMessage" />
        </div>
      </div>
    </div>
    
    <div v-else class="chat-input-wrapper">
      <ChatInput :disabled="isStreaming" @send="sendMessage" />
    </div>
  </div>
</template>

<style scoped>
.chat-app {
  position: relative;
  z-index: 1;
  display: flex;
  flex-direction: column;
  height: 100vh;
}

.demo-container {
  flex: 1;
  display: flex;
  align-items: center;
  justify-content: center;
  min-height: 0;
}

.demo-content {
  width: 100%;
  max-width: 720px;
  padding: 0 20px;
  text-align: center;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}

.demo-title {
  font-size: 36px;
  font-weight: 600;
  color: var(--text-primary);
  margin-bottom: 40px;
  text-shadow: 0 2px 20px rgba(0, 0, 0, 0.3);
}

.demo-input-wrapper {
  width: 100%;
}

.demo-input-wrapper .input-area {
  background: var(--bg-glass);
  border-top: none;
  border-radius: 24px;
  backdrop-filter: var(--glass-blur);
  -webkit-backdrop-filter: var(--glass-blur);
  box-shadow: 0 4px 24px rgba(0, 0, 0, 0.3);
  padding: 16px 20px;
}

.chat-main {
  flex: 1;
  overflow: hidden;
  display: flex;
  flex-direction: column;
}

.chat-input-wrapper {
  flex-shrink: 0;
}
</style>
