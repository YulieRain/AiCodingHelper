<script setup>
import { computed } from 'vue'
import { Sparkles } from 'lucide-vue-next'
import { renderMarkdown, renderPlainText } from '../utils/markdown.js'

const props = defineProps({
  role: {
    type: String,
    required: true,
  },
  content: {
    type: String,
    default: '',
  },
  timestamp: {
    type: Number,
    default: 0,
  },
  loading: {
    type: Boolean,
    default: false,
  },
  streaming: {
    type: Boolean,
    default: false,
  },
})

const renderedHtml = computed(() => {
  if (!props.content) return ''
  return props.role === 'assistant'
    ? renderMarkdown(props.content)
    : renderPlainText(props.content)
})

const timeLabel = computed(() => {
  if (!props.timestamp) return ''
  return new Date(props.timestamp).toLocaleTimeString('zh-CN', {
    hour: '2-digit',
    minute: '2-digit',
  })
})
</script>

<template>
  <div class="message-row" :class="role">
    <div v-if="role === 'assistant'" class="avatar">
      <Sparkles :size="14" :stroke-width="1.75" />
    </div>

    <div class="message-inner">
      <div
        class="bubble"
        :class="{
          loading,
          streaming: streaming && role === 'assistant',
        }"
      >
        <div v-if="loading && !content" class="typing-dots">
          <span></span>
          <span></span>
          <span></span>
        </div>
        <div
          v-else
          class="content"
          :class="[
            role === 'assistant' ? 'markdown-body' : 'plain-text-body',
            { 'streaming-cursor': streaming && role === 'assistant' },
          ]"
          v-html="renderedHtml"
        />
      </div>
      <time v-if="timeLabel && !loading" class="timestamp">{{ timeLabel }}</time>
    </div>
  </div>
</template>

<style scoped>
.message-row {
  display: flex;
  align-items: flex-end;
  gap: 10px;
  margin-bottom: 22px;
}

.message-row.user {
  flex-direction: row-reverse;
}

.avatar {
  width: 28px;
  height: 28px;
  border-radius: 50%;
  background: var(--accent-soft);
  border: 1px solid var(--accent-pink);
  display: flex;
  align-items: center;
  justify-content: center;
  color: var(--accent-pink);
  flex-shrink: 0;
  margin-bottom: 18px;
}

.message-inner {
  display: flex;
  flex-direction: column;
  gap: 4px;
  max-width: min(85%, 560px);
}

.message-row.user .message-inner {
  align-items: flex-end;
}

.message-row.assistant .message-inner {
  align-items: flex-start;
}

.bubble {
  padding: 11px 15px;
  border-radius: 18px;
  font-size: 14px;
  line-height: 1.7;
  word-break: break-word;
  backdrop-filter: blur(8px);
  -webkit-backdrop-filter: blur(8px);
}

.message-row.user .bubble {
  background: var(--bubble-user);
  border: 1px solid var(--bubble-user-border);
  border-bottom-right-radius: 6px;
  box-shadow: var(--shadow-sm);
}

.message-row.assistant .bubble {
  background: var(--bubble-assistant);
  border: 1px solid var(--border-color);
  border-bottom-left-radius: 6px;
}

.timestamp {
  font-size: 10px;
  color: var(--text-muted);
  padding: 0 4px;
}

.typing-dots {
  display: flex;
  gap: 4px;
  padding: 2px 0;
}

.typing-dots span {
  width: 5px;
  height: 5px;
  border-radius: 50%;
  background: var(--accent-pink);
  animation: dot-pulse 1.4s ease-in-out infinite;
}

.typing-dots span:nth-child(2) {
  animation-delay: 0.15s;
}

.typing-dots span:nth-child(3) {
  animation-delay: 0.3s;
}

@keyframes dot-pulse {
  0%,
  80%,
  100% {
    opacity: 0.35;
    transform: scale(0.85);
  }
  40% {
    opacity: 1;
    transform: scale(1);
  }
}
</style>
