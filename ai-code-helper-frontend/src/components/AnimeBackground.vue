<script setup>
defineProps({
  bgUrl: {
    type: String,
    default: '',
  },
  isTransitioning: {
    type: Boolean,
    default: false,
  },
})
</script>

<template>
  <div class="anime-bg" aria-hidden="true">
    <div
      class="anime-bg__image"
      :class="{ transitioning: isTransitioning }"
      :style="bgUrl ? { backgroundImage: `url(${bgUrl})` } : {}"
    />
    <div class="anime-bg__overlay" />
    <div class="anime-bg__glow anime-bg__glow--left" />
    <div class="anime-bg__glow anime-bg__glow--right" />
    <div class="anime-bg__glow anime-bg__glow--center" />
    <div class="anime-bg__sparkles" />
  </div>
</template>

<style scoped>
.anime-bg {
  position: fixed;
  inset: 0;
  z-index: 0;
  overflow: hidden;
  background: #0a0616;
}

.anime-bg__image {
  position: absolute;
  inset: -20px;
  background-size: cover;
  background-position: center;
  filter: brightness(0.5) saturate(1.1);
  transition: opacity 0.6s ease;
  opacity: 1;
}

.anime-bg__image.transitioning {
  opacity: 0.25;
}

.anime-bg__overlay {
  position: absolute;
  inset: 0;
  background:
    linear-gradient(180deg, rgba(6, 3, 18, 0.78) 0%, rgba(12, 5, 30, 0.72) 45%, rgba(6, 3, 18, 0.85) 100%),
    radial-gradient(ellipse at 50% 0%, rgba(250, 112, 154, 0.15) 0%, transparent 55%),
    radial-gradient(ellipse at 50% 100%, rgba(102, 126, 234, 0.12) 0%, transparent 55%);
}

.anime-bg__glow {
  position: absolute;
  border-radius: 50%;
  filter: blur(100px);
  pointer-events: none;
  opacity: 0.45;
  animation: float 8s ease-in-out infinite;
}

.anime-bg__glow--left {
  width: 380px;
  height: 380px;
  background: linear-gradient(135deg, #ff6b9d, #c44569);
  top: 5%;
  left: -10%;
  animation-delay: 0s;
}

.anime-bg__glow--right {
  width: 320px;
  height: 320px;
  background: linear-gradient(135deg, #a855f7, #6366f1);
  bottom: 10%;
  right: -8%;
  animation-delay: -4s;
}

.anime-bg__glow--center {
  width: 420px;
  height: 420px;
  background: linear-gradient(135deg, #f472b6, #c084fc);
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  opacity: 0.25;
  animation-delay: -2s;
}

.anime-bg__sparkles {
  position: absolute;
  inset: 0;
  background-image: 
    radial-gradient(2px 2px at 10% 20%, rgba(240, 171, 252, 0.6), transparent),
    radial-gradient(2px 2px at 20% 70%, rgba(244, 114, 182, 0.6), transparent),
    radial-gradient(1.5px 1.5px at 40% 40%, rgba(196, 132, 252, 0.6), transparent),
    radial-gradient(1.5px 1.5px at 70% 30%, rgba(240, 171, 252, 0.6), transparent),
    radial-gradient(2px 2px at 80% 60%, rgba(244, 114, 182, 0.6), transparent);
  animation: twinkle 6s ease-in-out infinite;
}

@keyframes float {
  0%, 100% {
    transform: translateY(0) scale(1);
  }
  50% {
    transform: translateY(-20px) scale(1.05);
  }
}

@keyframes twinkle {
  0%, 100% {
    opacity: 0.6;
  }
  50% {
    opacity: 1;
  }
}
</style>
