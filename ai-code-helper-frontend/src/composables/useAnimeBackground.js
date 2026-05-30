import { ref, onMounted, onUnmounted } from 'vue'

const WALLPAPER_API = 'https://api.horosama.com/random.php'
const ROTATE_INTERVAL = 90000

export function useAnimeBackground() {
  const bgUrl = ref('')
  const isLoading = ref(false)
  const isTransitioning = ref(false)
  let timer = null

  async function fetchWallpaper() {
    isLoading.value = true
    try {
      const res = await fetch(WALLPAPER_API, { method: 'GET' })
      if (!res.ok) throw new Error('fetch failed')
      const blob = await res.blob()
      const url = URL.createObjectURL(blob)
      await preloadImage(url)
      isTransitioning.value = true
      await new Promise((r) => setTimeout(r, 300))
      if (bgUrl.value && bgUrl.value.startsWith('blob:')) {
        URL.revokeObjectURL(bgUrl.value)
      }
      bgUrl.value = url
      await new Promise((r) => setTimeout(r, 400))
      isTransitioning.value = false
    } catch {
      if (!bgUrl.value) {
        bgUrl.value = ''
      }
    } finally {
      isLoading.value = false
    }
  }

  function preloadImage(url) {
    return new Promise((resolve, reject) => {
      const img = new Image()
      img.onload = resolve
      img.onerror = reject
      img.src = url
    })
  }

  function startAutoRotate() {
    stopAutoRotate()
    timer = setInterval(fetchWallpaper, ROTATE_INTERVAL)
  }

  function stopAutoRotate() {
    if (timer) {
      clearInterval(timer)
      timer = null
    }
  }

  onMounted(() => {
    fetchWallpaper()
    startAutoRotate()
  })

  onUnmounted(() => {
    stopAutoRotate()
    if (bgUrl.value && bgUrl.value.startsWith('blob:')) {
      URL.revokeObjectURL(bgUrl.value)
    }
  })

  return {
    bgUrl,
    isLoading,
    isTransitioning,
    fetchWallpaper,
    startAutoRotate,
    stopAutoRotate,
  }
}
