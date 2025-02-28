# Use an official Node.js runtime as a parent image
FROM node:20-alpine AS builder

# Set the working directory in the container
WORKDIR /app

# Copy package.json and package-lock.json to the working directory
COPY package*.json ./

# Install any needed dependencies specified in package.json
RUN npm install

# Copy all the files from the current directory to the working directory
COPY . .

# Use an official Node.js runtime as a parent image for production
FROM node:20-alpine AS runner

# Set working directory
WORKDIR /app

# Copy the application from builder
COPY --from=builder /app .


# Expose a port
EXPOSE 3000

# Set environment variables (Opcional, mas recomendado)
ENV NODE_ENV production

# Run the app when the container launches
CMD ["npm", "start"]