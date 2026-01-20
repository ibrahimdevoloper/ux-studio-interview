# Multi-stage build for React + Node.js
FROM node:18-alpine AS builder

WORKDIR /app

# Copy package files
COPY package*.json ./
COPY package-lock.json* ./

# Install all dependencies including devDependencies
RUN npm ci

# Copy source code
COPY . .

# Build the app
RUN npm run build

# Production stage
FROM node:18-alpine

WORKDIR /app

# Set to production environment
ENV NODE_ENV=production

# Install serve to run static files
RUN npm install -g serve

# Copy built files from builder stage
COPY --from=builder /app/build ./build

# Expose port (3000 is default for serve)
EXPOSE 3000

# Run the app
CMD ["serve", "-s", "build", "-l", "3000"]