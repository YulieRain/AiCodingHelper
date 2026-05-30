<script setup>
import { Sparkles, RotateCcw, ImageIcon } from 'lucide-vue-next'

defineProps({
  memoryId: {
    type: Number,
    required: true,
  },
  isDemo: {
    type: Boolean,
    default: false,
  },
  bgLoading: {
    type: Boolean,
    default: false,
  },
})

defineEmits(['clear', 'refresh-bg'])
</script>

<template>
  <header class="chat-header">
    <div class="header-inner">
      <div class="brand">
        <div class="brand-badge">
          <Sparkles :size="16" :stroke-width="1.75" />
        </div>
        <div class="brand-text">
          <h1 class="title">编程小助手</h1>
          <span class="subtitle">✦ 陪你学代码</span>
        </div>
      </div>
      <div class="header-actions">
        <span v-if="isDemo" class="demo-tag">示例</span>
        <span class="session-id">#{{ memoryId }}</span>
        <button
          class="icon-btn"
          title="换一张背景"
          :disabled="bgLoading"
          @click="$emit('refresh-bg')"
        >
          <ImageIcon :size="15" :stroke-width="1.75" />
        </button>
        <button class="icon-btn" title="清空对话" @click="$emit('clear')">
          <RotateCcw :size="15" :stroke-width="1.75" />
        </button>
      </div>
    </div>
  </header>
</template>

<style scoped>
.chat-header {
  height: var(--header-height);
  border-bottom: 1px solid var(--border-color);
  background: var(--bg-glass);
  backdrop-filter: var(--glass-blur);
  -webkit-backdrop-filter: var(--glass-blur);
  flex-shrink: 0;
  position: relative;
  z-index: 2;
}

.header-inner {
  max-width: var(--content-max-width);
  margin: 0 auto;
  height: 100%;
  padding: 0 20px;
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.brand {
  display: flex;
  align-items: center;
  gap: 12px;
}

.brand-badge {
  width: 36px;
  height: 36px;
  border-radius: 12px;
  background: linear-gradient(135deg, rgba(255, 142, 196, 0.3), rgba(212, 138, 255, 0.25));
  border: 1px solid rgba(255, 142, 196, 0.4);
  display: flex;
  align-items: center;
  justify-content: center;
  color: var(--accent-pink);
  box-shadow: 0 0 20px rgba(244, 114, 182, 0.3);
  animation: brand-glow 4s ease-in-out infinite;
}

.brand-text {
  display: flex;
  flex-direction: column;
  gap: 2px;
}

.title {
  font-size: 16px;
  font-weight: 600;
  color: var(--text-primary);
  letter-spacing: 0.02em;
}

.subtitle {
  font-size: 11px;
  color: var(--accent-purple);
  letter-spacing: 0.08em;
}

.header-actions {
  display: flex;
  align-items: center;
  gap: 10px;
}

.demo-tag {
  font-size: 10px;
  color: var(--accent-pink);
  padding: 3px 10px;
  border: 1px solid rgba(255, 142, 196, 0.35);
  border-radius: 999px;
  background: linear-gradient(135deg, rgba(255, 142, 196, 0.15), rgba(212, 138, 255, 0.1));
  box-shadow: 0 0 12px rgba(244, 114, 182, 0.2);
}

.session-id {
  font-size: 11px;
  color: var(--text-muted);
  font-variant-numeric: tabular-nums;
}

.icon-btn {
  display: flex;
  align-items: center;
  justify-content: center;
  width: 34px;
  height: 34px;
  border: 1px solid var(--border-color);
  border-radius: 10px;
  background: linear-gradient(135deg, rgba(255, 255, 255, 0.06), rgba(255, 255, 255, 0.03));
  color: var(--text-muted);
  cursor: pointer;
  transition: all 0.2s ease;
}

.icon-btn:hover:not(:disabled) {
  background: linear-gradient(135deg, rgba(255, 142, 196, 0.2), rgba(212, 138, 255, 0.15));
  border-color: rgba(255, 142, 196, 0.4);
  color: var(--accent-pink);
  transform: scale(1.05);
  box-shadow: 0 0 18px rgba(244, 114, 182, 0.3);
}

.icon-btn:disabled {
  opacity: 0.5;
  cursor: not-allowed;
}

@keyframes brand-glow {
  0%, 100% {
    box-shadow: 0 0 20px rgba(244, 114, 182, 0.3);
  }
  50% {
    box-shadow: 0 0 35px rgba(244, 114, 182, 0.5);
  }
}
</style>
