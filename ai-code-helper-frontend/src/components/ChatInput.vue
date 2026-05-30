<script setup>
import { ref, computed, watch, nextTick } from 'vue'
import { ArrowUp, Heart } from 'lucide-vue-next'

const props = defineProps({
  disabled: {
    type: Boolean,
    default: false,
  },
  noBorderTop: {
    type: Boolean,
    default: false,
  },
})

const emit = defineEmits(['send'])

const inputText = ref('')
const textareaRef = ref(null)

const canSend = computed(() => inputText.value.trim().length > 0 && !props.disabled)

function resizeTextarea() {
  const el = textareaRef.value
  if (!el) return
  el.style.height = 'auto'
  el.style.height = `${Math.min(el.scrollHeight, 160)}px`
}

watch(inputText, () => nextTick(resizeTextarea))

function handleSend() {
  const text = inputText.value.trim()
  if (!text || props.disabled) return
  emit('send', text)
  inputText.value = ''
  nextTick(resizeTextarea)
}

function handleKeydown(e) {
  if (e.key === 'Enter' && !e.shiftKey) {
    e.preventDefault()
    handleSend()
  }
}

function setText(text) {
  inputText.value = text
  nextTick(resizeTextarea)
}

defineExpose({ setText })
</script>

<template>
  <div class="input-area" :class="{ 'no-border-top': noBorderTop }">
    <div class="input-wrapper">
      <div class="input-box" :class="{ disabled }">
        <textarea
          ref="textareaRef"
          v-model="inputText"
          class="input-field"
          placeholder="有什么编程问题想问我～"
          rows="1"
          :disabled="disabled"
          @keydown="handleKeydown"
          @input="resizeTextarea"
        />
        <button
          class="send-btn"
          :class="{ active: canSend }"
          :disabled="!canSend"
          title="发送"
          @click="handleSend"
        >
          <ArrowUp :size="18" :stroke-width="2" />
        </button>
      </div>
      <p class="hint">
        <Heart :size="12" :stroke-width="2" class="hint-icon" />
        Enter 发送 · Shift+Enter 换行
      </p>
    </div>
  </div>
</template>

<style scoped>
.input-area {
  flex-shrink: 0;
  padding: 12px 20px 20px;
  background: var(--bg-glass);
  backdrop-filter: var(--glass-blur);
  -webkit-backdrop-filter: var(--glass-blur);
  border-top: 1px solid var(--border-color);
  position: relative;
  z-index: 2;
}

.input-area.no-border-top {
  border-top: none;
}

.input-wrapper {
  max-width: var(--content-max-width);
  margin: 0 auto;
}

.input-box {
  display: flex;
  align-items: center;
  gap: 10px;
  padding: 16px 14px 16px 18px;
  background: rgba(255, 255, 255, 0.05);
  border: 1px solid var(--border-color);
  border-radius: 24px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.2);
  transition: border-color 0.2s, box-shadow 0.2s;
}

.input-box:focus-within {
  border-color: rgba(240, 171, 252, 0.4);
  box-shadow: 0 0 20px rgba(240, 171, 252, 0.2);
}

.input-box.disabled {
  opacity: 0.6;
}

.input-field {
  flex: 1;
  min-height: 24px;
  max-height: 160px;
  border: none;
  outline: none;
  background: transparent;
  color: var(--text-primary);
  font-size: 16px;
  line-height: 1.5;
  resize: none;
  font-family: inherit;
  text-align: center;
}

.input-field::placeholder {
  color: var(--text-muted);
  text-align: center;
}

.input-field:disabled {
  cursor: not-allowed;
}

.send-btn {
  flex-shrink: 0;
  width: 40px;
  height: 40px;
  border-radius: 12px;
  border: none;
  background: rgba(255, 255, 255, 0.08);
  color: var(--text-muted);
  cursor: not-allowed;
  display: flex;
  align-items: center;
  justify-content: center;
  transition: all 0.2s ease;
}

.send-btn.active {
  background: linear-gradient(135deg, #ff8ec4, #d48aff);
  color: #fff;
  cursor: pointer;
}

.send-btn.active:hover {
  transform: scale(1.05);
}

.hint {
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 6px;
  font-size: 12px;
  color: var(--text-muted);
  margin-top: 12px;
}

.hint-icon {
  color: rgba(244, 114, 182, 0.6);
}
</style>
