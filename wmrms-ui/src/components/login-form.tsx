import { cn } from "@/lib/utils"
import { Button } from "@/components/ui/button"
import { Input } from "@/components/ui/input"
import { Label } from "@/components/ui/label"
import { useState } from "react"
import { useNavigate } from "react-router-dom"

export function LoginForm({
  className,
  ...props
}: React.ComponentProps<"form">) {

  const [email, setEmail] = useState("")
  const [password, setPassword] = useState("")
  const [error, setError] = useState("")
   const navigate = useNavigate()

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault()
    setError("")

    try {
      const res = await fetch("http://localhost:3001/users")
      const users = await res.json()
      const user = users.find((u: { email: string }) => u.email === email)

      console.log("user",user);
      

      if (!user) {
      setError("User not found")
      return
    }
    
      if (user.password !== password) {
        setError("Incorrect password")
        return
      }

      localStorage.setItem("user", JSON.stringify(user))

      if (user.role === "admin") navigate("/admin")
      else if (user.role === "doctor") navigate("/doctor")
      else if (user.role === "manager") navigate("/manager")
      else setError("Invalid user role")

    } catch (err) {
      console.error(err)
      setError("Something went wrong")
    }
  }
  return (
    <form onSubmit={handleSubmit} className={cn("flex flex-col gap-6", className)} {...props}>
      <div className="flex flex-col items-center gap-2 text-center">
        <h1 className="text-2xl font-bold">Login to your account</h1>
        <p className="text-muted-foreground text-sm text-balance">
          Enter your email below to login to your account
        </p>
      </div>
      <div className="grid gap-6">
        <div className="grid gap-3">
          <Label htmlFor="email">Email</Label>
          <Input 
          id="email" 
          type="email" 
          placeholder="m@example.com" 
          value={email} 
          onChange={(e)=> setEmail(e.target.value)}
          required 
          />
        </div>
        <div className="grid gap-3">
          <div className="flex items-center">
            <Label htmlFor="password">Password</Label>
            <a
              href="#"
              className="ml-auto text-sm underline-offset-4 hover:underline"
            >
              Forgot your password?
            </a>
          </div>
          <Input 
          id="password" 
          type="password" 
          value={password} 
          onChange={(e)=> setPassword(e.target.value)}
          required 
          />
        </div>
        {error && <p className="text-red-500 text-sm">{error}</p>}
        <Button type="submit" className="w-full">
          Login
        </Button>
      </div>
      <div className="text-center text-sm">
        Don&apos;t have an account?{" "}
        <a href="#" className="underline underline-offset-4">
          Sign up
        </a>
      </div>
    </form>
  )
}
