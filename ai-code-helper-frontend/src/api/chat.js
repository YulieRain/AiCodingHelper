import api from './index.js'

/**
 * 通过 SSE 流式调用 chat 接口
 * 浏览器端流式响应需使用 fetch，axios 实例用于统一 baseURL 配置
 */
export async function chatStream(memoryId, message, { onChunk, onDone, onError, signal }) {
  const baseURL = api.defaults.baseURL
  const url = `${baseURL}/ai/chat?memoryId=${memoryId}&message=${encodeURIComponent(message)}`

  try {
    const response = await fetch(url, { signal })

    if (!response.ok) {
      throw new Error(`请求失败: ${response.status}`)
    }

    const reader = response.body.getReader()
    const decoder = new TextDecoder()
    let buffer = ''

    while (true) {
      const { done, value } = await reader.read()
      if (done) break

      buffer += decoder.decode(value, { stream: true })
      const events = buffer.split('\n\n')
      buffer = events.pop() || ''

      for (const event of events) {
        const lines = event.split('\n')
        for (const line of lines) {
          if (line.startsWith('data:')) {
            const data = line.slice(5).trimStart()
            if (data) onChunk?.(data)
          }
        }
      }
    }

    if (buffer.trim()) {
      const lines = buffer.split('\n')
      for (const line of lines) {
        if (line.startsWith('data:')) {
          const data = line.slice(5).trimStart()
          if (data) onChunk?.(data)
        }
      }
    }

    onDone?.()
  } catch (err) {
    if (err.name !== 'AbortError') {
      onError?.(err)
    }
  }
}
